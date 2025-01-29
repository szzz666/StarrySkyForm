package top.szzz666.nukkit_plugin.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.nukkit_plugin.Main.ec;
import static top.szzz666.nukkit_plugin.form.easy_form.Modal.confirmModal;
import static top.szzz666.nukkit_plugin.tools.pluginUtil.multCmd;
import static top.szzz666.nukkit_plugin.tools.taskUtil.Async;

public class OkFormCommand extends Command {
    public OkFormCommand() {
        super(ec.getString("okFormCommand"), "需要确定执行的命令");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Async(() -> {
                if (args.length < 1){
                    return;
                }
                Player player = (Player) sender;
                StringBuilder cmd = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    cmd.append(args[i]).append(" ");
                }
                confirmModal(player, args[0], () -> multCmd(player, cmd.toString()));
            });
        }
        return true;
    }
}
