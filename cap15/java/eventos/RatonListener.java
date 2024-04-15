package eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed en clase pública");
	}
}
