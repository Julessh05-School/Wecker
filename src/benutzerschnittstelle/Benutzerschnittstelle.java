package benutzerschnittstelle;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import steuerung.Steuerung;
import java.io.File;
import java.awt.Font;
import javax.swing.SwingConstants;

public final class Benutzerschnittstelle extends JFrame
{
	private final JPanel contentPane;
	private final JButton btnMode;
	private final JButton btnSet;
	private final JLabel lblDoppelpunkt;
	private final JLabel lblMinuten;
	private final JLabel lblStunden;
	private final JLabel lblWeckfunktion;
	private final JLabel lblZeitart;

	public final class AlarmThread extends Thread
	{
		public void run()
		{
			final Color hintergrundfarbe = contentPane.getBackground();
			while (aktivAlarm)
			{
				// akustisches Wecksignal

				try
				{
					final AudioInputStream audioInputStream = AudioSystem
							.getAudioInputStream(new File("beep.wav"));
					final Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				}
				catch (Exception e)
				{
					System.out.println(e.getLocalizedMessage());
				}

				// optisches Wecksignal

				if (contentPane.getBackground() == hintergrundfarbe)
				{
					contentPane.setBackground(Color.RED);
				}
				else
				{
					contentPane.setBackground(hintergrundfarbe);
				}

				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					System.out.println(e.getLocalizedMessage());
				}
			}

			contentPane.setBackground(hintergrundfarbe);
		}
	}

	private AlarmThread derAlarm;
	private boolean aktivAlarm = false;
	private final Steuerung dieSteuerung;

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}

		EventQueue.invokeLater(() -> {
			try
			{
				final Benutzerschnittstelle frame = new Benutzerschnittstelle();
				frame.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

	public Benutzerschnittstelle()
	{
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("PC-Wecker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 165, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnMode = new JButton("mode");
		btnMode.addActionListener(arg0 -> gedruecktMode());
		btnMode.setBounds(10, 106, 65, 23);
		contentPane.add(btnMode);

		btnSet = new JButton("set");
		btnSet.addActionListener(e -> gedruecktSet());
		btnSet.setBounds(80, 106, 65, 23);
		contentPane.add(btnSet);

		lblStunden = new JLabel("00");
		lblStunden.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStunden.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblStunden.setBounds(10, 50, 55, 45);
		contentPane.add(lblStunden);

		lblMinuten = new JLabel("00");
		lblMinuten.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinuten.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblMinuten.setBounds(90, 50, 55, 45);
		contentPane.add(lblMinuten);

		lblDoppelpunkt = new JLabel(":");
		lblDoppelpunkt.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoppelpunkt.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblDoppelpunkt.setBounds(68, 48, 20, 45);
		contentPane.add(lblDoppelpunkt);

		lblWeckfunktion = new JLabel("lblWeckfunktion");
		lblWeckfunktion.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeckfunktion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWeckfunktion.setBounds(10, 11, 135, 14);
		contentPane.add(lblWeckfunktion);

		lblZeitart = new JLabel("lblZeitart");
		lblZeitart.setHorizontalAlignment(SwingConstants.CENTER);
		lblZeitart.setBounds(10, 31, 135, 14);
		contentPane.add(lblZeitart);

		dieSteuerung = new Steuerung(this);
	}

	private void gedruecktMode()
	{
		dieSteuerung.gedruecktMode();
	}

	private void gedruecktSet()
	{
		dieSteuerung.gedruecktSet();
	}

	public void schalteAusAlarm()
	{
		aktivAlarm = false;
	}

	public void schalteEinAlarm()
	{
		aktivAlarm = true;
		derAlarm = new AlarmThread();
		derAlarm.start();
	}

	public void zeigeMinuten(int pMinuten, boolean pEinstellbar)
	{
		final String mm = String.format("%02d", pMinuten);
		lblMinuten.setText(mm);

		if (pEinstellbar)
		{
			lblMinuten.setForeground(Color.RED);
		}
		else
		{
			lblMinuten.setForeground(Color.BLACK);
		}
	}

	public void zeigeStunden(int pStunden, boolean pEinstellbar)
	{
		final String hh = String.format("%2d", pStunden);
		lblStunden.setText(hh);

		if (pEinstellbar)
		{
			lblStunden.setForeground(Color.RED);
		}
		else
		{
			lblStunden.setForeground(Color.BLACK);
		}
	}

	public void zeigeWeckfunktion(boolean pEingeschaltet, boolean pEinstellbar)
	{
		if (pEingeschaltet)
		{
			lblWeckfunktion.setText("Wecken ein");
		}
		else
		{
			lblWeckfunktion.setText("Wecken aus");
		}

		if (pEinstellbar)
		{
			lblWeckfunktion.setForeground(Color.RED);
		}
		else
		{
			lblWeckfunktion.setForeground(Color.BLACK);
		}
	}

	public void zeigeZeitart(char pZeitart)
	{
		switch (pZeitart) {
			case 'A' -> lblZeitart.setText("aktuelle Zeit");
			case 'W' -> lblZeitart.setText("Weckzeit");
		}
	}
}
