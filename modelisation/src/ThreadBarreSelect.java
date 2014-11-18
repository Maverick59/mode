public class ThreadBarreSelect extends Thread {

	private final Ecran ecran;

	ThreadBarreSelect(Ecran ecran) {
		this.ecran = ecran;
	}

	@Override
	public void run() {
		if (ecran.getBarreSelect().isOuvert()) {
			ouvrir();
		} else {
			fermer();
		}
		ecran.getBarreSelect().switchposition();
	}

	private void fermer() {
		while (ecran.getBarreSelect().getX() > ecran.getWidth()
				- ecran.getBarreSelect().getWidth()) {
			ecran.getBarreSelect().setLocation(
					ecran.getBarreSelect().getX()
							- ecran.getBarreSelect().getWidth() / 20,
					ecran.getBarreSelect().getY());
			ecran.getBarreSelect().repositionnerBouton();
			try {
				this.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreSelect().setLocation(
				ecran.getWidth() - ecran.getBarreSelect().getWidth(), 0);
		ecran.getBarreSelect().repositionnerBouton();
	}

	private void ouvrir() {
		while (ecran.getBarreSelect().getX() < ecran.getWidth()) {
			ecran.getBarreSelect().setLocation(
					ecran.getBarreSelect().getX()
							+ ecran.getBarreSelect().getWidth() / 20,
					ecran.getBarreSelect().getY());
			ecran.getBarreSelect().repositionnerBouton();
			try {
				this.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		ecran.getBarreSelect().setLocation(ecran.getWidth() + 1, 0);
		ecran.getBarreSelect().repositionnerBouton();

	}

}
