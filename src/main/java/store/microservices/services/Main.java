package store.microservices.services;

import store.microservices.services.products.ProductsServer;
import store.microservices.services.registration.RegistrationServer;

/**
 * Allow the servers to be invoked from the command-line. The jar is built with
 * this as the <code>Main-Class</code> in the jar's <code>MANIFEST.MF</code>.
 * 
 * @author Ruslan Paluektau
 */
public class Main {

	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[1]);
			// Fall through into ..

		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		switch (serverName) {
			case "registration":
			case "reg":
				RegistrationServer.main(args);
				break;
			case "products":
				ProductsServer.main(args);
				break;
			default:
				System.out.println("Unknown server type: " + serverName);
				usage();
				break;
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println(
				"     where server-name is 'reg', 'registration', " + "'accounts' or 'web' and server-port > 1024");
	}
}
