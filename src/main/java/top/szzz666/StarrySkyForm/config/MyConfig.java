package top.szzz666.StarrySkyForm.config;


import static top.szzz666.StarrySkyForm.Main.ec;
import static top.szzz666.StarrySkyForm.Main.plugin;

public class MyConfig {
    public static void initConfig() {
        plugin.saveResource("forms/main.json");
        ec = new EasyConfig("config.yml", plugin);
        ec.add("command", "openform");
        ec.add("okFormCommand", "okform");
        ec.add("argFormCommand", "argform");
        ec.add("spFormCommand", "spform");
        ec.add("tipsFormCommand", "tipsform");
        ec.add("spargFormCommand", "spargform");
        ec.add("clearInventoryCommand", "cleargfi");
        ec.add("item", "347:0:1:8:星空菜单");
        ec.load();
    }

}
