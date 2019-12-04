package pl.B4GU5.Utils;

public class modsListClass
{
    int id;
    String name;
    Boolean addon;
    String md5;
    
    public modsListClass() {
    }
    
    public modsListClass(final int id, final String name, final String addon, final String md5) {
        this.id = id;
        this.name = name;
        this.addon = new Boolean(addon);
        this.md5 = md5;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public boolean isAddon() {
        return this.addon;
    }
    
    public void setAddon(final String addon) {
        this.addon = new Boolean(addon);
    }
    
    public String getHash() {
        return this.md5;
    }
    
    public void setHash(final String md5) {
        this.md5 = md5;
    }
}
