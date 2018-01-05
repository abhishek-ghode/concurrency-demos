package com.seed;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;

public class PanelWithProgressBar extends JPanel {

	private Task task;
	private final JProgressBar progressBar;
	private final JButton startButton;
	private final JTextArea taskOutput;

	public PanelWithProgressBar() {
		// TODO:1 Discuss following comment
		/*
		 * For PanelWithProgressBar panel, set Layout Manager as BorderLayout
		 * This panel will contain another panel, which holds Button & Progress
		 * Bar, in PAGE_START region. And TextArea will be in CENTER region
		 */
		super(new BorderLayout());

		JPanel panel = new JPanel();

		startButton = new JButton("Start");
		startButton.setActionCommand("start");

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		panel.add(startButton);
		panel.add(progressBar);
		add(panel, BorderLayout.PAGE_START);

		// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		taskOutput = new JTextArea(5, 20);
		taskOutput.setMargin(new Insets(5, 5, 5, 5));
		taskOutput.setEditable(false);
		add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// TODO:2 Please refer Task type before checking these listeners

		// TODO:5 Discuss following comment
		/*
		 * This listener will be invoked whenever Task's property changes
		 */
		final PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("progress" == evt.getPropertyName()) {
					int progress = (Integer) evt.getNewValue();
					progressBar.setValue(progress);
					taskOutput.append(String.format(
							"Completed %d%% of task.\n", task.getProgress()));
				}
			}
		};

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startButton.setEnabled(false);
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				// TODO:6 Discuss following comment
				/*
				 * Instances of javax.swing.SwingWorker are not reusuable, so we
				 * create new instances as needed.
				 */

				task = new Task();
				task.addPropertyChangeListener(propertyChangeListener);
				
// 				TODO:7 Remove comment for the preceding method call & comment the method call for TO DO:8 , UI freezes
//				 task.doInBackground();
				
				// TODO:8 Now remove comment for the preceding method call & comment the method call for TO DO:7, UI won't freeze
				task.execute();


			}
		});

	}

	class Task extends SwingWorker<Void, Void> {
		// TODO:3 Discuss following comment
		/*
		 * This task will be executed by background thread. It won't be
		 * scheduled in event handling thread, because it may freeze UI of the
		 * application
		 */
		@Override
		public Void doInBackground() {
			// System.out.println(Thread.currentThread().getName());
			Random random = new Random();
			int progress = 0;
			// Initialize progress property.
			setProgress(0);
			while (progress < 100) {
				// Sleep for up to one second.
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException ignore) {
				}
				// Make random progress.
				progress += random.nextInt(10);
				setProgress(Math.min(progress, 100));
			}
			return null;
		}

		// TODO:4 Discuss following comment
		/*
		 * This method is scheduled for the Event DispatchThread after the
		 * doInBackground method is finished It is overridden to perform
		 * completion actions
		 */

		@Override
		public void done() {
			Toolkit.getDefaultToolkit().beep();
			startButton.setEnabled(true);
			setCursor(null); // turn off the wait cursor
			taskOutput.append("Done!\n");
		}

	}

}