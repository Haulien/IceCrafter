package org.rearmed.IceCrafter;

import java.io.*;
import java.util.*;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * IceCrafter for Bukkit
 *
 * @author Steven Saric
 * @version 0.2
 */

public class IceCrafter extends JavaPlugin
{

    Config pluginSettings;
    protected String pluginMainDir = "./plugins/IceCrafter";
    protected String pluginConfigLocation = pluginMainDir + "/config.cfg";


    protected String detailStr = "[" + "IceCrafter" + " v" + "0.2" + "] ";

    public void onEnable()
    {
        try
        {

            Properties preSettings = new Properties();

            if ((new File(pluginConfigLocation)).exists())
            {
                preSettings.load(new FileInputStream(new File(pluginConfigLocation)));
                pluginSettings = new Config(preSettings, this, true);

                if (!pluginSettings.upToDate)
                {
                    pluginSettings.createConfig();
                }

                System.out.println(detailStr + "Existing config successfully loaded!");

            }
            else
            {
                pluginSettings = new Config(preSettings, this, false);
                pluginSettings.createConfig();
                System.out.println(detailStr + "New config successfully created!");

            }



            // Adding ice recipe
            if (pluginSettings.craftableIce)
            {
                getServer().addRecipe(pluginSettings.iceRecipe);
            }

            PluginDescriptionFile pdfFile = this.getDescription();
            System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");




        }
        catch (IOException e)
        {
            System.out.println(detailStr + "Could not load IceCrafter configuration! " + e);
        }
        catch (Exception e)
        {
            System.out.println(detailStr + "IceCrafter::OnEnable :: Something unknown went wrong! " + e);
        }

    }


    public void onDisable()
    {
        System.out.println("IceCrafter disabled!");
    }

}

