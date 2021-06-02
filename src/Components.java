import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.*;

public class Components {

    private static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    private static Color getColor(String colorString){
        String color = colorString.toLowerCase();
        switch (color){
            case "red":
                return Color.RED;
            case "gray":
                return Color.GRAY;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.blue;
            case "yellow":
                return Color.YELLOW;
            default:
                return null;
        }
    }

    public static void attachBorderLayout(Container container) {
        container.setLayout(new BorderLayout());
    }

    public static void attachGridLayout(Container container, Element element) {
        GridLayout gridLayout = new GridLayout();
        if (element.hasAttribute("cols")) {
            gridLayout.setColumns(Integer.parseInt(element.getAttribute("cols")));
        } else {
            throw new IllegalStateException("required attr: cols");
        }
        if (element.hasAttribute("rows")) {
            gridLayout.setRows(Integer.parseInt(element.getAttribute("rows")));
        } else {
            throw new IllegalStateException("required attr: rows");
        }
        container.setLayout(gridLayout);
    }

    public static void attachFlowLayout(Container container, Element element) {
        FlowLayout flowLayout = new FlowLayout();
        int alignment;
        if (element.hasAttribute("alignment")) {
            switch (element.getAttribute("alignment")) {
                case "left":
                    alignment = FlowLayout.LEFT;
                    break;
                case "right":
                    alignment = FlowLayout.RIGHT;
                    break;
                case "center":
                    alignment = FlowLayout.CENTER;
                    break;
                default:
                    throw new IllegalStateException("invalid argument for attr alignment");
            }
            flowLayout.setAlignment(alignment);
            container.setLayout(flowLayout);
        } else {
            throw new IllegalStateException("required attr: cols");
        }
    }

    public static void addToBorderLayout(Element element, JComponent component, Container parent) {
        if (element.hasAttribute("layout_gravity"))
            parent.add(component, capitalize(element.getAttribute("layout_gravity")));
        else
            parent.add(component);
    }

    public static JFrame parseJFrame(Element root) {
        JFrame jFrame = new JFrame();
        if (root.hasAttribute("title"))
            jFrame.setTitle(root.getAttribute("title"));
        if (root.hasAttribute("width"))
            jFrame.setSize(Integer.parseInt(root.getAttribute("width")), jFrame.getHeight());
        else
            throw new IllegalStateException("missing JFrame attr: width");
        if (root.hasAttribute("height"))
            jFrame.setSize(jFrame.getWidth(), Integer.parseInt(root.getAttribute("height")));
        else
            throw new IllegalStateException("missing JFrame attr: height");
        return jFrame;
    }

    public static void attachLayout(Element element, Container container) {
        if (element.hasAttribute("layout")) {
            switch (element.getAttribute("layout")) {
                case "BorderLayout":
                    attachBorderLayout(container);
                    break;
                case "GridLayout":
                    attachGridLayout(container, element);
                    break;
                case "FlowLayout":
                    attachFlowLayout(container, element);
                    break;
            }
        }
    }

}
