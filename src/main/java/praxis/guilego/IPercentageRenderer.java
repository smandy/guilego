package praxis.guilego;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class IPercentageRenderer extends PraxisDefaultTableCellRenderer<IPercentDone> {

    public IPercentageRenderer(String name, IPercentDone def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret =  (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
        IPercentDone cumWrapper = getValue(value);
		progressPercent = cumWrapper.getPercentDone();
		//progressPercent = cumWrapper.progressPercent();
		myText = Integer.toString( progressPercent );
		
		repaint();
		return ret;
	}

    @Override
    public IPercentDone getValueImpl(Object o) {
        return (IPercentDone)o;
    }

    private String myText;
	private int progressPercent;
	
		@Override
	   public void paint(Graphics g) 
	    {
			double progress = 0.5;

			int xoff = 1;
			int yoff = 1;
			int height = getHeight() - 3;
			int width = getWidth() - 2;
			int progressX = xoff;
			if (progress > 0) {
				g.setColor((progressPercent < 100) ? Color.CYAN : Color.GREEN.brighter().brighter() );
				progressX = (width * progressPercent) / 100;
				g.fillRect(xoff, yoff, progressX, height);
			}

			if (myText != null && myText.length() > 0) {
				FontMetrics fm = g.getFontMetrics();
				int w = fm.stringWidth(myText);

				int x = (getWidth() - w) / 2;
				int y = getHeight() - 4;

				g.setColor(getForeground());
				g.drawString( myText, (x > 1) ? x : 1, y);
			}						
			g.setColor(Color.lightGray);
			g.drawRect(1, 1, getWidth() - 2, getHeight() - 3);
			//super.paint(g);
	    }

        @Override
        public String getText(IPercentDone t) {
            return Integer.toString( t.getPercentDone());
        }
}
