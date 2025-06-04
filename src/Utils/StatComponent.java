package Utils;
import javax.swing.*;
public class StatComponent {
    private int value;
    private String icon;
    private JPanel component;
    public StatComponent(int value, String icon) {
        this.value = value;
        this.icon = icon;
        this.component = component;
    }
    public int getValue() {
        return value;
    }

    public String getIcon() {
        return icon;
    }

    public JPanel getComponent() {
        return component;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // Se quiser atualizar o texto visual:
    public void updateValueLabel() {
        JLabel label = (JLabel) component.getComponent(1); // segundo componente (label de texto)
        label.setText(value + "%");
    }
}
