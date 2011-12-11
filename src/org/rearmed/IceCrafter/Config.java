package org.rearmed.IceCrafter;

import java.io.*;
import java.util.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;


/**
 * Editable configuration class (user input)
 *
 * @author Steven Saric
 */

public class Config implements java.io.Serializable
{

    private final int iceID = 79;

    private Properties properties;
    private final IceCrafter plugin;
    public boolean upToDate = true;

    // List of Config Options
    boolean craftableIce = true;


    String detailStr = "[IceCraft] ";

    // The Ice Crafting Recipe
    ShapedRecipe iceRecipe;

    public Config(Properties p, final IceCrafter plugin, boolean customRecipe) throws NoSuchElementException
    {
        properties = p;
        this.plugin = plugin;

        detailStr = this.plugin.detailStr;

        // Ice Recipe Default
        iceRecipe = new ShapedRecipe(new ItemStack(iceID, 1));
        iceRecipe.shape("SSS", "SWS", "SSS");
        iceRecipe.setIngredient('S', Material.SNOW_BALL);
        iceRecipe.setIngredient('W', Material.WATER_BUCKET);

        // Grab values here.

        if (customRecipe)
        {
            iceRecipe = getRecipe();
        }

    }


    public String getString(String label) throws NoSuchElementException
    {
        String value = properties.getProperty(label);
        if (value == null)
        {
            upToDate = false;
            throw new NoSuchElementException(detailStr + "Config did not contain: " + label);
        }

        return value;
    }


    public ShapedRecipe getRecipe()
    {
        String topRow = "ABC";
        String middleRow = "DEF";
        String bottomRow = "GHI";
        ShapedRecipe preRecipe = new ShapedRecipe(new ItemStack(iceID, 1));
        preRecipe.shape(topRow, middleRow, bottomRow);

        try
        {
            preRecipe.setIngredient('A', Material.valueOf(getString("a").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("A", " ");
        }

        try
        {
            preRecipe.setIngredient('B', Material.valueOf(getString("b").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("B", " ");
        }

        try
        {
            preRecipe.setIngredient('C', Material.valueOf(getString("c").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("C", " ");
        }

        try
        {
            preRecipe.setIngredient('D', Material.valueOf(getString("d").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("D", " ");
        }

        try
        {
            preRecipe.setIngredient('E', Material.valueOf(getString("e").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("E", " ");
        }

        try
        {
            preRecipe.setIngredient('F', Material.valueOf(getString("f").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("F", " ");
        }

        try
        {
            preRecipe.setIngredient('G', Material.valueOf(getString("g").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("G", " ");
        }

        try
        {
            preRecipe.setIngredient('H', Material.valueOf(getString("h").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("H", " ");
        }

        try
        {
            preRecipe.setIngredient('I', Material.valueOf(getString("i").toUpperCase()));
        }
        catch (Exception e)
        {
            topRow.replace("I", " ");
        }

        preRecipe.shape(topRow, middleRow, bottomRow);
        return preRecipe;
    }

    public void createConfig()
    {
        try
        {


            File createDir = new File(plugin.pluginMainDir);

            if (!(createDir.exists()))
            {

                boolean dirCreated = false;

                int retries = 15;


                while (!dirCreated && retries != 0)
                {
                    retries--;
                    dirCreated = createDir.mkdir();
                }

                if (!dirCreated)
                {
                    System.out.println(detailStr + "CreateConfig :: Directory failed to create. No permissions?");
                    return;
                }

            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(plugin.pluginConfigLocation)));

            out.write("#\r\n");
            out.write("# IceCrafter Configuration\r\n");
            out.write("\r\n");
            out.write("\r\n");
            out.write("# Choose your materials according to the\r\n");
            out.write("# diagram below.To indicate an empty slot\r\n");
            out.write("# leave the value blank.\r\n");
            out.write("# You can get the list of material names from:\r\n");
            out.write("# http://jd.bukkit.org/apidocs/org/bukkit/Material.html\r\n");
            out.write("# Remember that you must put the exact names.\r\n");
            out.write("\r\n");
            out.write("# a b c\r\n");
            out.write("# d e f\r\n");
            out.write("# g h i\r\n");
            out.write("\r\n");
            out.write("a=SNOW_BALL\r\n");
            out.write("b=SNOW_BALL\r\n");
            out.write("c=SNOW_BALL\r\n");
            out.write("\r\n");
            out.write("d=SNOW_BALL\r\n");
            out.write("e=WATER_BUCKET\r\n");
            out.write("f=SNOW_BALL\r\n");
            out.write("\r\n");
            out.write("g=SNOW_BALL\r\n");
            out.write("h=SNOW_BALL\r\n");
            out.write("i=SNOW_BALL\r\n");
            out.close();
        }
        catch (Exception e)
        {
            //something went wrong
        }

    }
}
