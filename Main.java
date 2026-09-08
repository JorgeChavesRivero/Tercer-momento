import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		String[] ofertas = {"Combo Cuidado Personal", "Combo Hogar", "Combo Despensa", null, null};
		int opcion;
		do {
			System.out.println("Control de ofertas");
			System.out.println("Seleccione una opción:");
			System.out.println("1. Ver ofertas");
			System.out.println("2. Validar oferta");
			System.out.println("3. Registrar nueva oferta");
			System.out.println("4. Salir");
			opcion = leer.nextInt();
			leer.nextLine();

			switch (opcion) {
			case 1:
				System.out.println("Ofertas disponibles:");
				for (int i = 0; i < ofertas.length; i++) {
					if (ofertas[i] != null) {
						System.out.println("- " + ofertas[i]);
					}
				}
				break;
			case 2:
				System.out.print("Ingrese el nombre de la oferta: ");
				String nombreProducto = leer.nextLine().trim();
				if (buscarProducto(ofertas, nombreProducto)) {
					System.out.println("La oferta está en el catalogo.");
				} else {
					System.out.println("La oferta no está en el catalogo.");
				}
				break;
			case 3:
				System.out.print("Ingrese el nombre de la nueva oferta: ");
				String nuevaOferta = leer.nextLine().trim();
				if (nuevaOferta.isEmpty()) {
					System.out.println("El nombre de la oferta no puede estar vacío.");
				} else if (agregarCombo(ofertas, nuevaOferta)) {
					System.out.println("Oferta agregada correctamente.");
				} else {
					System.out.println("No hay espacio disponible.");
				}
				break;
			case 4:
				System.out.println("Programa finalizado.");
				break;
			default:
				System.out.println("Opción no válida.");
				break;
			}
		} while (opcion != 4);
		leer.close();
	}

	public static boolean buscarProducto(String[] ofertas, String nombreBuscado) {
		for (int i = 0; i < ofertas.length; i++) {
			if (ofertas[i] != null && ofertas[i].equalsIgnoreCase(nombreBuscado)) {
				return true;
			}
		}
		return false;
	}

	public static boolean agregarCombo(String[] ofertas, String nuevaOferta) {
		for (int i = 0; i < ofertas.length; i++) {
			if (ofertas[i] == null) {
				ofertas[i] = nuevaOferta;
				System.out.println("Oferta registrada en la posición " + (i + 1));
				return true;
			}
		}
		return false;
	}
}
