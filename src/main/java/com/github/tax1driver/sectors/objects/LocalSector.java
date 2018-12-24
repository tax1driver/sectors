package com.github.tax1driver.sectors.objects;

import com.mongodb.*;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.jongo.Jongo;

public class LocalSector {
    private SectorConfiguration config;
    private RedisClient redisClient;
    private RedisAsyncCommands<String, String> redisCmds;
    private Jongo jongo;


    public static void initializeSector() throws Exception {
        if (localSector != null)
            throw new Exception("tried to reinitialize local sector");

        localSector.config = SectorConfiguration.fromLocalConfiguration();
        localSector.redisClient = RedisClient.create(RedisURI.builder().withSentinel(localSector.config.getRedisAddress()).withPassword(localSector.config.getRedisPassword()).build());

        localSector.redisCmds = localSector.redisClient.connect().async();

        MongoClient cli = new MongoClient(new ServerAddress(localSector.config.getMongoDBAddress()), MongoCredential.createCredential(localSector.config.getMongoDBUser(),
                localSector.config.getMongoDBDatabaseName(), localSector.config.getMongoDBPassword().toCharArray()), MongoClientOptions.builder().build());

        DB db = cli.getDB(localSector.config.getMongoDBDatabaseName());
        localSector.jongo = new Jongo(db);




    }

    public SectorConfiguration getConfig() {
        return config;
    }

    public RedisAsyncCommands<String, String> getRedis() {
        return redisCmds;
    }

    public Jongo getJongo() {
        return jongo;
    }

    public static LocalSector getLocalSector() {
        return localSector;
    }

    private static LocalSector localSector;
}
