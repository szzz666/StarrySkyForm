package top.szzz666.StarrySkyForm.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.form.element.ElementLabel;
import top.szzz666.StarrySkyForm.form.easy_form.Custom;
import top.szzz666.StarrySkyForm.form.easy_form.Modal;
import top.szzz666.StarrySkyForm.form.easy_form.Simple;

import static top.szzz666.StarrySkyForm.Main.ec;
import static top.szzz666.StarrySkyForm.tools.taskUtil.Async;

public class TipsFormCommand extends Command {
    public TipsFormCommand() {
        super(ec.getString("tipsFormCommand"), "弹窗提示");
        this.setPermission("starry.sky.form.command");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newEnum("opt", new String[]{"modal", "custom", "simple"})
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Async(() -> {
                if (args.length < 2) {
                    return;
                }
                Player player = (Player) sender;
                StringBuilder msg = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    msg.append(args[i]).append(" ");
                }
                String type = args[0];
                switch (type) {
                    case "modal" -> {
                        Modal modal = new Modal(args[1], msg.toString(), "确定", "关闭");
                        modal.asyncShow(player);
                    }
                    case "custom" -> {
                        Custom custom = new Custom(args[1]);
                        custom.add("", new ElementLabel(msg.toString()));
                        custom.asyncShow(player);
                    }
                    case "simple" -> {
                        Simple simple = new Simple(args[1], msg.toString());
                        simple.asyncShow(player);
                    }
                }
            });
        }
        return true;
    }
}
