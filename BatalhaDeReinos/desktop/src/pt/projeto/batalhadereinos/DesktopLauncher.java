package pt.projeto.batalhadereinos;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import pt.projeto.batalhadereinos.BatalhaDeReinos;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Batalha de Reinos");
		config.setWindowedMode(1440, 1024);
		config.setResizable(false);
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new BatalhaDeReinos(), config);
	}
}
