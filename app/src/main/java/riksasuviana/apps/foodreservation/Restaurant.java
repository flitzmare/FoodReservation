package riksasuviana.apps.foodreservation;

/**
 * Created by User on 1/13/2017.
 */

public class Restaurant {
    private String photo;
    private String name;
    public Restaurant(String photo, String name){
        this.setPhoto(photo);
        this.setName(name);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
