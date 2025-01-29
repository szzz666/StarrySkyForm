package top.szzz666.nukkit_plugin.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import top.szzz666.nukkit_plugin.form.easy_form.Custom;

import static top.szzz666.nukkit_plugin.Main.ec;
import static top.szzz666.nukkit_plugin.Main.nkServer;
import static top.szzz666.nukkit_plugin.tools.pluginUtil.getPlayerNameList;
import static top.szzz666.nukkit_plugin.tools.pluginUtil.multCmd;
import static top.szzz666.nukkit_plugin.tools.taskUtil.Async;

public class SpFormCommand extends Command {

        public SpFormCommand() {
            super(ec.getString("spFormCommand"), "需要选择玩家执行的命令");
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
                    Custom custom = new Custom("选择玩家");
                    custom.add("s", new ElementDropdown(args[0],getPlayerNameList()));
                    custom.setSubmit(() -> multCmd(sender, cmd + " " + custom.getDropdownRes("s")));
                    custom.asyncShow(player);
                });
            }
            return true;
        }
}
