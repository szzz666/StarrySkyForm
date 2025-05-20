package top.szzz666.StarrySkyForm.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import top.szzz666.StarrySkyForm.form.easy_form.Custom;

import static top.szzz666.StarrySkyForm.Main.ec;
import static top.szzz666.StarrySkyForm.tools.pluginUtil.getPlayerNameList;
import static top.szzz666.StarrySkyForm.tools.pluginUtil.multCmd;
import static top.szzz666.StarrySkyForm.tools.taskUtil.Async;

public class SpArgFormCommand extends Command {

    public SpArgFormCommand() {
        super(ec.getString("spargFormCommand"), "需要选择玩家和参数执行的命令");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Async(() -> {
                if (args.length < 1) {
                    return;
                }
                Player player = (Player) sender;
                StringBuilder cmd = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    cmd.append(args[i]).append(" ");
                }
                Custom custom = new Custom("选择玩家");
                custom.add("s", new ElementDropdown(args[0], getPlayerNameList()));
                custom.add("c", new ElementInput(args[1]));
                custom.setSubmit(() -> multCmd(sender, cmd + " \"" + custom.getDropdownRes("s") + "\" " + custom.getInputRes("c")));
                custom.asyncShow(player);
            });
        }
        return true;
    }
}
