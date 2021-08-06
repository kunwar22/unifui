package in.co.srdt.unif.model.video;

public class HelpVideos {
    private String title;
    private String path;
    private String description;
    private long helpid;

    public HelpVideos() {
    }

    public HelpVideos(String title, String path, String description, long helpid) {
        this.title = title;
        this.path = path;
        this.description = description;
        this.helpid = helpid;
    }

    @Override
    public String toString() {
        return "HelpVideos{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", helpid=" + helpid +
                '}';
    }

    public long getHelpid() {
        return helpid;
    }

    public void setHelpid(long helpid) {
        this.helpid = helpid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
