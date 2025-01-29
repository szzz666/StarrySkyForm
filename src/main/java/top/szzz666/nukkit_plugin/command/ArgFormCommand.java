package top.szzz666.nukkit_plugin.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import top.szzz666.nukkit_plugin.form.easy_form.Custom;

import static top.szzz666.nukkit_plugin.Main.ec;
import static top.szzz666.nukkit_plugin.tools.pluginUtil.multCmd;
import static top.szzz666.nukkit_plugin.tools.taskUtil.Async;

public class ArgFormCommand extends Command {
    public ArgFormCommand() {
        super(ec.getString("argFormCommand"), "需要参数执行的命令");
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
                Custom custom = new Custom("输入");
                custom.add("c", new ElementInput(args[0]));
                custom.setSubmit(() -> multCmd(sender, cmd + " " + custom.getInputRes("c")));
                custom.asyncShow(player);
            });
        }
        return true;
    }
}
