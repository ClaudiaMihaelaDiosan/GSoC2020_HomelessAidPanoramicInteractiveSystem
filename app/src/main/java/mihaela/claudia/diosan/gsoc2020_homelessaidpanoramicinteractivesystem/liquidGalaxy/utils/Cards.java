package mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.utils;

public class Cards {
    private int image;
    private String title;
    private int backgroundColor;

    public Cards(){}

    public Cards(int image, String title, int backgroundColor){
        this.image = image;
        this.title = title;
        this.backgroundColor = backgroundColor;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
