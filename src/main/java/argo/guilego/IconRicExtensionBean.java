package argo.guilego;

import javax.swing.*;

public class IconRicExtensionBean implements Comparable<IconRicExtensionBean> {

    final String ricExtension;
    final ImageIcon imageIcon;

    public String getRicExtension() {
        return ricExtension;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public IconRicExtensionBean(String ricExtension, ImageIcon imageIcon) {
        this.ricExtension = ricExtension;
        this.imageIcon = imageIcon;
    }

    @Override
    public int compareTo(IconRicExtensionBean o) {
        return ricExtension.compareTo(o.getRicExtension());
    }
}
