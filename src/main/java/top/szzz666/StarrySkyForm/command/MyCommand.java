package top.szzz666.StarrySkyForm.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.StarrySkyForm.Main.ec;
import static top.szzz666.StarrySkyForm.tools.pluginUtil.openForm;


public class MyCommand extends Command {
    public MyCommand() {
        super(ec.getString("command"), "打开菜单");
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Player player = (Player) sender;
            openForm(sender, args, player);
        }
        return true;
    }


}
