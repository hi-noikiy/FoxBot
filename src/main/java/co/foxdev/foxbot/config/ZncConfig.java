package co.foxdev.foxbot.config;

import co.foxdev.foxbot.FoxBot;
import co.foxdev.foxbot.config.yamlconfig.file.FileConfiguration;
import co.foxdev.foxbot.config.yamlconfig.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ZncConfig
{
    private FoxBot foxbot;

    private File configFile = new File("znc.yml");
    private FileConfiguration zncConfig;

    private String nick;
    private String altNick;
    private String ident;
    private String quitMsg;
    private int bufferCount;
    private boolean denySetBindhost;
    private List<String> modules;

    public ZncConfig(FoxBot foxbot)
    {
        this.foxbot = foxbot;
        zncConfig = new YamlConfiguration();
        loadConfig();
    }

    private void loadConfig()
    {
        zncConfig.saveResource("znc.yml", false);

        try
        {
            zncConfig.load(configFile);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        nick = zncConfig.getString("users.nick");
        altNick = zncConfig.getString("users.alt-nick");
        ident = zncConfig.getString("users.ident");
        quitMsg = zncConfig.getString("users.quit-message");
        bufferCount = zncConfig.getInt("users.buffercount");
        denySetBindhost = zncConfig.getBoolean("users.deny-set-bindhost");
        modules = zncConfig.getStringList("users.modules");
    }

    public boolean networkExists(String network)
    {
        return zncConfig.contains("networks." + network);
    }

    // -------------
    // Users section
    // -------------

    public String getNick()
    {
        return nick;
    }

    public String getAltNick()
    {
        return altNick;
    }

    public String getIdent()
    {
        return ident;
    }

    public String getQuitMsg()
    {
        return quitMsg;
    }

    public int getBufferCount()
    {
        return bufferCount;
    }

    public boolean isDenySetBindhost()
    {
        return denySetBindhost;
    }

    public List<String> getModules()
    {
        return modules;
    }

    // ---------------
    // Servers section
    // ---------------

    public List<String> getServers(String network)
    {
        return zncConfig.getStringList("networks." + network + ".servers");
    }

    public String getNetworkName(String network)
    {
        return zncConfig.getString("networks." + network + ".name");
    }

    public List<String> getChannels(String network)
    {
        return zncConfig.getStringList("networks." + network + ".channels");
    }
}
