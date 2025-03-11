package edu.esiea.examandroid.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import edu.esiea.examandroid.enums.PlaceType;

@Entity(tableName = "place")
public class PlaceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String website;
    private double latitude;
    private double longitude;
    private PlaceType type;

    public PlaceEntity() {
    }

    public PlaceEntity(int id, String name, String description, String phoneNumber,
                       String email, String website, double latitude,
                       double longitude, PlaceType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public PlaceType getType() { return type; }
    public void setType(PlaceType type) { this.type = type; }
}
