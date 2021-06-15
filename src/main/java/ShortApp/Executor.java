package ShortApp;

import javafx.application.Platform;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class Executor implements Runnable {

	private final Controller parent;
	private final String instruction;
	private final Text commandOutput;

	public Executor(Controller parent, String instruction, Text commandOutput) {
		this.parent = parent;
		this.instruction = instruction;
		this.commandOutput = commandOutput;
	}

	@Override
	public void run() {
		commandOutput.setText("Please Wait...");
		if (parent.Regex("cmd *", instruction)) {
			commandOutput.setText(parent.exec(instruction.substring(4), true));
		} else if (parent.Regex("cmd", instruction)) {
			parent.exec("cmd /c start cmd.exe", false);
		} else if (parent.Regex("ipconfig", instruction)) {
			try {
				commandOutput.setText(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		} else if (parent.Regex("c:", instruction)) {
			parent.exec("explorer C:", false);
		} else if (parent.Regex("dev", instruction)) {
			parent.exec("explorer C:\\DEV", false);
		} else if (parent.Regex("home", instruction)) {
			parent.exec("explorer " + System.getProperty("user.home"), false);
		} else if (parent.Regex("desktop", instruction)) {
			parent.exec("explorer " + System.getProperty("user.home") + "\\Desktop", false);
		} else if (parent.Regex("notepad", instruction)) {
			parent.exec("notepad", false);
		} else if (parent.Regex("google *", instruction)) {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(URI.create("www.google.com/search?q=" + instruction.substring(7).replace(" ", "+")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (parent.Regex("www.*.*", instruction)) {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(URI.create(instruction));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (parent.Regex("min", instruction)) {
			Platform.runLater(parent::minimize);
		} else if (parent.Regex("clear", instruction)) {
			commandOutput.setText("");
		} else if (parent.Regex("stop", instruction) || parent.Regex("exit", instruction) || parent.Regex("quit", instruction)) {
			System.exit(0);
		} else {
			commandOutput.setText("Please Check Your Syntax");
		}
		if (commandOutput.getText().equals("Please Wait...")) {
			commandOutput.setText("");
		}
	}
}
