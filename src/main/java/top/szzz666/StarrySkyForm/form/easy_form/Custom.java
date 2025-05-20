package top.szzz666.StarrySkyForm.form.easy_form;

import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.handler.FormResponseHandler;
import cn.nukkit.form.window.FormWindowCustom;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static top.szzz666.StarrySkyForm.tools.taskUtil.Async;


@Data
public class Custom {
    private final List<String> elements = new ArrayList<>();
    private FormWindowCustom form;
    private Runnable close;
    private Runnable submit;

    public Custom(String title) {
        this.form = new FormWindowCustom(title);
    }

    public String add(String Label) {
        String key = getRandKey();
        this.elements.add(key);
        this.form.addElement(new ElementLabel(Label));
        return key;
    }

    private String getRandKey() {
        return "custom_" + Math.random();
    }

    public void add(String key, Element element) {
        this.elements.add(key);
        this.form.addElement(element);
    }

    public void show(Player player) {
        this.form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (this.form.wasClosed()) {
                if (this.close != null) {
                    this.close.run();
                }
                return;
            }
            if (this.submit != null) {
                this.submit.run();
            }
        }));
        player.showFormWindow(this.form);
    }

    public void asyncShow(Player player) {
        this.form.addHandler(FormResponseHandler.withoutPlayer(ignored -> Async(() -> {
            if (this.form.wasClosed()) {
                if (this.close != null) {
                    this.close.run();
                }
                return;
            }
            if (this.submit != null) {
                this.submit.run();
            }
        })));
        player.showFormWindow(this.form);
    }

    @SuppressWarnings("unchecked")
    public <T> T getRes(String key) {
        return (T) this.form.getResponse().getResponse(elements.indexOf(key));
    }

    public String getInputRes(String key) {
        return this.form.getResponse().getInputResponse(elements.indexOf(key));
    }

    public String getDropdownRes(String key) {
        return this.form.getResponse().getDropdownResponse(elements.indexOf(key)).getElementContent();
    }

    public String getStepSliderRes(String key) {
        return this.form.getResponse().getStepSliderResponse(elements.indexOf(key)).getElementContent();
    }

    public int getStepSliderIdRes(String key) {
        return this.form.getResponse().getStepSliderResponse(elements.indexOf(key)).getElementID();
    }

    public int getDropdownIndexRes(String key) {
        return this.form.getResponse().getDropdownResponse(elements.indexOf(key)).getElementID();
    }

    public float getSliderRes(String key) {
        return this.form.getResponse().getSliderResponse(elements.indexOf(key));
    }

    public boolean getToggleRes(String key) {
        return this.form.getResponse().getToggleResponse(elements.indexOf(key));
    }

}
