package top.szzz666.StarrySkyForm;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import top.szzz666.StarrySkyForm.command.*;
import top.szzz666.StarrySkyForm.config.EasyConfig;
import top.szzz666.StarrySkyForm.event.Listeners;

import static top.szzz666.StarrySkyForm.config.MyConfig.initConfig;
import static top.szzz666.StarrySkyForm.tools.pluginUtil.nkConsole;
import static top.szzz666.StarrySkyForm.tools.pluginUtil.pluginNameLineConsole;


public class Main extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static EasyConfig ec;

    //插件读取
    @Override
    public void onLoad() {
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        ec = new EasyConfig("config.yml", plugin);
        initConfig();
        nkConsole("&b" + plugin.getName() + "插件读取...");
    }

    //插件开启
    @Override
    public void onEnable() {
        //注册监听器
        nkServer.getPluginManager().registerEvents(new Listeners(), this);
        //注册命令
        nkServer.getCommandMap().register(this.getName(), new MyCommand());
        nkServer.getCommandMap().register(this.getName(), new ArgFormCommand());
        nkServer.getCommandMap().register(this.getName(), new OkFormCommand());
        nkServer.getCommandMap().register(this.getName(), new SpFormCommand());
        nkServer.getCommandMap().register(this.getName(), new TipsFormCommand());
        nkServer.getCommandMap().register(this.getName(), new SpArgFormCommand());
        nkServer.getCommandMap().register(this.getName(), new ClearInventoryCommand());
        pluginNameLineConsole();
        nkConsole("&b" + plugin.getName() + "插件开启");
        nkConsole("&c" + plugin.getName() + "如果遇到任何bug，请加入Q群进行反馈：894279534", 1);
//        String buttonShow = "Button" + "&" + "textures/items/apple.png";
//        ArrayList<String> commands = new ArrayList<>();
//        commands.add("say hello");
//        commands.add("say world");
//        HashMap<String, ArrayList<String>> buttons = new HashMap<>();
//        buttons.put(buttonShow, commands);
//        Form form = new Form("测试表单", "测试内容", buttons);
//        nkConsole(form.toJson());
    }

    //插件关闭
    @Override
    public void onDisable() {
        nkConsole("&b" + plugin.getName() + "插件关闭");
    }

}
