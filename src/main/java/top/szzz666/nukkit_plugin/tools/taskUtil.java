package top.szzz666.nukkit_plugin.tools;

import cn.nukkit.scheduler.AsyncTask;

import static top.szzz666.nukkit_plugin.Main.nkServer;
import static top.szzz666.nukkit_plugin.Main.plugin;


public class taskUtil {
    //    Async(() -> {});
    public static void Async(Runnable logic) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                logic.run();
            }
        });
    }

    //    Delayed(() -> {}, 20, true);
    public static void Delayed(Runnable logic, int delay, boolean asynchronous) {
        nkServer.getScheduler().scheduleDelayedTask(plugin, logic, delay, asynchronous);
    }

    //    Repeating(() -> {}, 20, true);
    public static void Repeating(Runnable logic, int delay, boolean asynchronous) {
        nkServer.getScheduler().scheduleRepeatingTask(plugin, logic, delay, asynchronous);
    }
}
