package top.szzz666.nukkit_plugin.tools;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import io.leego.banana.BananaUtils;
import io.leego.banana.Font;
import lombok.SneakyThrows;
import top.szzz666.nukkit_plugin.entity.Form;
import top.szzz666.nukkit_plugin.form.easy_form.Simple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static top.szzz666.nukkit_plugin.Main.*;
import static top.szzz666.nukkit_plugin.tools.taskUtil.Async;

public class pluginUtil {
    // 获取所有在线玩家的名字
    public static ArrayList<String> getPlayerNameList() {
        ArrayList<String> allPlayerNames = new ArrayList<>();
        for (Player serverPlayer : nkServer.getOnlinePlayers().values()) {
            allPlayerNames.add(serverPlayer.getName());
        }
        return allPlayerNames;
    }
    public static void openForm(CommandSender sender, String[] args, Player player) {
        Async(() -> {
            String arg = args.length == 1 ? args[0] : "main";
            String path = ConfigPath + "/forms/" + arg + ".json";
            String content;
            try {
                content = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                sender.sendMessage("§c" + path + " 文件不存在");
                return;
            }
            Gson gson = new Gson();
            Form form = gson.fromJson(content, Form.class);
            Simple simple = new Simple(form.getTitle(), form.getContent());
            for (String buttonShow : form.getButtons().keySet()) {
                String[] split = buttonShow.split("&");
                String text = split[0];
                String img = split.length > 1 ? split[1] : null;
                ArrayList<String> commands = form.getButtons().get(buttonShow);
                if (img == null) {
                    simple.add(text, () -> multCmds(sender, commands));
                } else {
                    simple.add(text, img, () -> multCmds(sender, commands));
                }
            }
            simple.asyncShow(player);
        });
    }

    public static void multCmds(CommandSender sender, ArrayList<String> commands) {
        for (String command : commands) {
            nkServer.getCommandMap().dispatch(sender, command);
        }
    }

    public static void multCmd(CommandSender sender, String command) {
        nkServer.getCommandMap().dispatch(sender, command);
    }

    //Banana
    @SneakyThrows
    public static void pluginNameLineConsole() {
        lineConsole(BananaUtils.bananaify(plugin.getName(), Font.SMALL));
    }

    //将输入的字符串按行打印到控制台。
    public static void lineConsole(String s) {
        String[] lines = s.split("\n");
        for (String line : lines) {
            nkConsole(line);
        }
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg) {
        plugin.getLogger().info(TextFormat.colorize('&', msg));
    }

    public static void nkConsole(String msg, int typeNum) {
        if (typeNum == 1) {
            plugin.getLogger().warning(TextFormat.colorize('&', msg));
        } else if (typeNum == 2) {
            plugin.getLogger().error(TextFormat.colorize('&', msg));
        } else {
            plugin.getLogger().info(TextFormat.colorize('&', msg));
        }
    }
}
