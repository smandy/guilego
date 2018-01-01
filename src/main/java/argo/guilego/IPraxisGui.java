package argo.guilego;


public interface IArgoGui {
    void cancelWaveClicked();

    void selectAll();

    void cancelOrderClicked();

    void cancelBasketClicked();


    void setInterlinkSending(boolean transmitting);

    FrameFilters getFilterFrame();

    void onPositionSelectionChanged();

    boolean hasImage();

    void showFilterFrame();

    void showOrderEntry();

    void positionFiltersChanged();

    void unSilenceSelectedAlerts();

    void silenceSelectedAlerts();

    public OrderEntryFrame getOrderFrame();
}
