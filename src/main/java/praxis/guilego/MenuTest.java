/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuTest.java
 *
 * Created on 28-Aug-2009, 08:29:57
 */

package praxis.guilego;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author ansmith
 */
public class MenuTest extends javax.swing.JFrame {
	
	static final Log log = LogFactory.getLog(  MenuTest.class.getName()  );

	/** Creates new form MenuTest */
    public MenuTest() {
        initComponents();
        // installMousePopupListener();
        jTable1.addMouseListener( new MousePopupListener( jTable1, new MyListener() ) );
    }
    
	public static class MousePopupListener extends MouseAdapter {
	    @SuppressWarnings("serial")
		abstract class MyMenuItem extends JMenuItem  {
	    	public MyMenuItem(String string) {
	    		super(string);
	    		this.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						run();
					}} );
	    	}
	    	abstract void run( );
	    }
		
        private JPopupMenu popup;
		private IOrderMenuListener listener;
		private Component parent;
		
		MousePopupListener( Component _p, IOrderMenuListener _l )
		{
			this.parent   = _p;
			this.listener = _l;
			
	    	popup = new JPopupMenu();
	    	popup.add( new MyMenuItem("CANCEL") {
	    		@Override
	    		public void run() {
	    			listener.cancel();
	    		}
	    	}  );
	    	popup.add( new MyMenuItem("CLOSE") {
				@Override
				void run() {
					listener.close();
				}} );
	    	JMenu sliceMenu = new JMenu("SLICE");
	    	for (int i = 10;i<100;i+=10)
	    	{
	    		final int pct = i;
	    		sliceMenu.add( new MyMenuItem( Integer.toString(pct) + "%" ) {
					@Override
					void run() {
						listener.sliceByPercent(pct);
					}} );
	    	}
	    	popup.add( sliceMenu );
		}
		
    	public void mousePressed(MouseEvent e) {
    		checkPopup(e);
    	}

    	public void mouseClicked(MouseEvent e) {
    		checkPopup(e);
    	}

    	public void mouseReleased(MouseEvent e) {
    		checkPopup(e);
    	}

    	private void checkPopup(MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			popup.show(parent, e.getX(), e.getY());
    			System.out.println();
    		}
    	}
    }
	
	public interface IOrderMenuListener
	{
		public void send();
		
		public void close();

		public void cancel();
		
		public void manuallyCancel();
		
		public void sliceByPercent(int pct);
	}
	
	
	public class MyListener implements IOrderMenuListener
	{
		@Override
		public void cancel() {
			log.info("cancel");
		}

		@Override
		public void manuallyCancel() {
			log.info("manually cancel");
		}

		@Override
		public void send() {
			log.info("send");
		}

		@Override
		public void sliceByPercent(int pct) {
			if ( log.isInfoEnabled() ) {log.info("sliceByPercent " + pct); };
		}

		@Override
		public void close() {
			log.info("close");
		}
	}
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}