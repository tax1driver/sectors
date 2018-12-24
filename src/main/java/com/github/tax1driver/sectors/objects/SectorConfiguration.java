package com.github.tax1driver.sectors.objects;


import com.github.tax1driver.sectors.PluginMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SectorConfiguration {
    private SimplifiedLocation sectorMinLocation;
    private SimplifiedLocation sectorMaxLocation;

    private String sectorLeftBorder;
    private String sectorTopBorder;
    private String sectorRightBorder;
    private String sectorBottomBorder;

    private String mongoDBAddress;
    private String mongoDBUser;
    private String mongoDBPassword;
    private String mongoDBDatabaseName;

    public SimplifiedLocation getSectorMinLocation() {
        return sectorMinLocation;
    }

    public void setSectorMinLocation(SimplifiedLocation sectorMinLocation) {
        this.sectorMinLocation = sectorMinLocation;
    }

    public SimplifiedLocation getSectorMaxLocation() {
        return sectorMaxLocation;
    }

    public void setSectorMaxLocation(SimplifiedLocation sectorMaxLocation) {
        this.sectorMaxLocation = sectorMaxLocation;
    }

    public String getSectorLeftBorder() {
        return sectorLeftBorder;
    }

    public void setSectorLeftBorder(String sectorLeftBorder) {
        this.sectorLeftBorder = sectorLeftBorder;
    }

    public String getSectorTopBorder() {
        return sectorTopBorder;
    }

    public void setSectorTopBorder(String sectorTopBorder) {
        this.sectorTopBorder = sectorTopBorder;
    }

    public String getSectorRightBorder() {
        return sectorRightBorder;
    }

    public void setSectorRightBorder(String sectorRightBorder) {
        this.sectorRightBorder = sectorRightBorder;
    }

    public String getSectorBottomBorder() {
        return sectorBottomBorder;
    }

    public void setSectorBottomBorder(String sectorBottomBorder) {
        this.sectorBottomBorder = sectorBottomBorder;
    }

    public String getMongoDBAddress() {
        return mongoDBAddress;
    }

    public void setMongoDBAddress(String mongoDBAddress) {
        this.mongoDBAddress = mongoDBAddress;
    }

    public String getMongoDBUser() {
        return mongoDBUser;
    }

    public void setMongoDBUser(String mongoDBUser) {
        this.mongoDBUser = mongoDBUser;
    }

    public String getMongoDBPassword() {
        return mongoDBPassword;
    }

    public void setMongoDBPassword(String mongoDBPassword) {
        this.mongoDBPassword = mongoDBPassword;
    }

    public String getMongoDBDatabaseName() {
        return mongoDBDatabaseName;
    }

    public void setMongoDBDatabaseName(String mongoDBDatabaseName) {
        this.mongoDBDatabaseName = mongoDBDatabaseName;
    }


    public String getRedisAddress() {
        return redisAddress;
    }

    public void setRedisAddress(String redisAddress) {
        this.redisAddress = redisAddress;
    }

    public String getRedisUser() {
        return redisUser;
    }

    public void setRedisUser(String redisUser) {
        this.redisUser = redisUser;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    private String redisAddress;
    private String redisUser;
    private String redisPassword;

    private SectorConfiguration(FileConfiguration fc) {
        sectorMinLocation = SimplifiedLocation.fromString(fc.getString("sector.boundaries.min"));
        sectorMaxLocation = SimplifiedLocation.fromString(fc.getString("sector.boundaries.max"));

        sectorLeftBorder = fc.getString("sector.borders.left");
        sectorTopBorder = fc.getString("sector.borders.left");
        sectorRightBorder = fc.getString("sector.borders.left");
        sectorBottomBorder = fc.getString("sector.borders.left");

        mongoDBAddress = fc.getString("sectors.database.address");
        mongoDBUser = fc.getString("sectors.database.user");
        mongoDBPassword = fc.getString("sectors.database.password");
        mongoDBDatabaseName = fc.getString("sectors.database.dbname");

        redisAddress = fc.getString("sectors.sharedcache.address");
        redisUser = fc.getString("sectors.sharedcache.user");
        redisPassword = fc.getString("sectors.sharedcache.password");
    }

    public static SectorConfiguration fromLocalConfiguration() {
        FileConfiguration fc = YamlConfiguration.loadConfiguration(new File(PluginMain.getInstance().getDataFolder(), "sector.yml"));
        return new SectorConfiguration(fc);
    }
}
