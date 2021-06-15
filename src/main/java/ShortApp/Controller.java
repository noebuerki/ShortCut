package ShortApp;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	private final Text commandOutput;
	private final Stage parent;

	public Controller(Text commandOutput, Stage parent) {
		this.commandOutput = commandOutput;
		this.parent = parent;
	}

	public void handelInput(String input) {
		if (!input.equals("")) {
			new Thread(new Executor(this, input, commandOutput)).start();
		} else {
			commandOutput.setText("");
		}
	}

	public String exec(String command, boolean response) {
		try {
			String message = "";
			Runtime rt = Runtime.getRuntime();
			Process process = rt.exec(command);

			if (response) {

				BufferedReader stdInput = new BufferedReader(new
						InputStreamReader(process.getInputStream()));

				BufferedReader stdError = new BufferedReader(new
						InputStreamReader(process.getErrorStream()));

				String s = null;
				while ((s = stdInput.readLine()) != null) {
					message += s + "\n";
				}

				while ((s = stdError.readLine()) != null) {
					message += s + "\n";
				}
				return message;
			} else {
				return "";
			}
		} catch (IOException e) {
			commandOutput.setText("Please Check Your Syntax");
			return "";
		}
	}

	public boolean Regex(String first, String second) {

		if (first.length() == 0 && second.length() == 0)
			return true;

		if (first.length() > 1 && first.charAt(0) == '*' &&
				second.length() == 0)
			return false;

		if ((first.length() > 1 && first.charAt(0) == '?') ||
				(first.length() != 0 && second.length() != 0 &&
						first.charAt(0) == second.charAt(0)))
			return Regex(first.substring(1),
					second.substring(1));

		if (first.length() > 0 && first.charAt(0) == '*')
			return Regex(first.substring(1), second) ||
					Regex(first, second.substring(1));
		return false;
	}

	public void maximize() {
		parent.toFront();
		parent.requestFocus();
	}

	public void minimize() {
		parent.toBack();
	}
}
