package top.szzz666.nukkit_plugin.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.nukkit_plugin.Main.ec;
import static top.szzz666.nukkit_plugin.event.Listeners.giveFormItem;
import static top.szzz666.nukkit_plugin.tools.pluginUtil.openForm;


public class MyCommand extends Command {
    public MyCommand() {
        super(ec.getString("command"), "打开菜单");
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Player player = (Player) sender;
            if (args.length == 1 && args[0].equals("clear")){
                player.getInventory().clearAll();
                giveFormItem(player);
                return true;
            }
            openForm(sender, args, player);
        }
        return true;
    }


}
