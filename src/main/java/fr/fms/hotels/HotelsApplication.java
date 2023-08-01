package fr.fms.hotels;

import fr.fms.hotels.entities.City;
import fr.fms.hotels.entities.Hotel;
import fr.fms.hotels.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class HotelsApplication implements CommandLineRunner {
	@Autowired
	HotelServiceImpl hotelServiceImpl;
	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		generateDataHotel();
	}
	private void generateDataHotel() {
		City toulouse = hotelServiceImpl.saveCity(new City(null, "Toulouse", "Ville rose", null));
		City nice = hotelServiceImpl.saveCity(new City(null, "Nice", "Ville au bord de la méditerranée", null));
		City saintAmand = hotelServiceImpl.saveCity(new City(null, "Saint Amand Les Eaux", "Ville thermale", null));
		City rennes = hotelServiceImpl.saveCity(new City(null, "Rennes", "Ville proche de la forêt de Brocéliande", null));
		City strasbourg = hotelServiceImpl.saveCity(new City(null, "Strasbourg", "Ville avec de la bonne choucroute", null));
		City lille = hotelServiceImpl.saveCity(new City(null, "Lille", "Ville proche de la Belgique", null));
		hotelServiceImpl.saveHotel(new Hotel(null, "Eklo Hotel", "181 Av. de Grande Bretagne, 31300 Toulouse", "05 82 95 923 1", 30, 2, 52, "eklo.png", toulouse));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel Gascogne", "25 All. Charles de Fitte, 31300 Toulouse", "05 61 59 27 44", 20, 3, 65, "hotelgascogne.png", toulouse));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel Toulouse Centre", "8 Rue Rivals, 31000 Toulouse", "05 61 21 17 91", 40, 3, 84, "hoteltoulousecentre.png", toulouse));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel des Beaux Arts", "1 Pl. du Pont Neuf, 31000 Toulouse", "05 34 45 42 42", 15, 2, 86, "hoteldesbeauxarts.png", toulouse));
		hotelServiceImpl.saveHotel(new Hotel(null, "Le Palladia", "271 Av. de Grande Bretagne, 31300 Toulouse", "05 62 12 01 20", 31, 4, 92, "palladia.png", toulouse));
		hotelServiceImpl.saveHotel(new Hotel(null, "Le Negresco", "37 Prom. des Anglais, 06000 Nice", "04 93 16 64 00", 70, 5, 735, "negresco.png", nice));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel Nice Excelsior", "19 Av. Durante, 06000 Nice", "04 93 88 1805", 50, 4, 240, "excelsio.png", nice));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel Danemark", "3 Bis Av. des Baumettes, 06000 Nice", "04 93 44 12 04", 15, 2, 128, "danemark.png", nice));
		hotelServiceImpl.saveHotel(new Hotel(null, "Le Saint Paul", "29 Bd Franck Pilatte, 06300 Nice", "04 93 89 39 57", 20, 3, 170, "saintpaul.png", nice));
		hotelServiceImpl.saveHotel(new Hotel(null, "Au Château Des Termes", "58 Rue du 2 Septembre 1944, 59230 Saint-Amand-les-Eaux", "03 07 50 92 85", 18, 2, 60, "auchateaudestermes.png", saintAmand));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel du Pasino", "Chem. de l'Empire, 59230 Saint-Amand-les-Eaux", "03 27 48 20 20", 40, 2, 81, "pasino.png", saintAmand));
		hotelServiceImpl.saveHotel(new Hotel(null, "Mama Shelter", "3 Pl. de la Trinité, 35000 Rennes", "02 57 67 70 00", 20, 4, 128, "mamashelter.png", rennes));
		hotelServiceImpl.saveHotel(new Hotel(null, "Balthazar Hôtel & Spa", "19 Rue Maréchal Joffre, 35000 Rennes", "02 99 32 32 32", 25, 5, 166, "balthazar.png", rennes));
		hotelServiceImpl.saveHotel(new Hotel(null, "Le Florin Hotel", "13 Pl. de la Gare, 35000 Rennes", "02 23 40 48 13", 27, 2, 63, "leflorin.png", rennes));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hilton", "1 Av. Herrenschmidt, 67000 Strasbourg", "03 88 37 10 10", 21, 4, 85, "hilton.png", strasbourg));
		hotelServiceImpl.saveHotel(new Hotel(null, "The People", "7 Rue de la Krutenau, 67000 Strasbourg", "09 78 36 20 27", 10, 2, 59, "unknown.png", strasbourg));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel des Vosges", "3 Pl. de la Gare, 67000 Strasbourg", "03 88 62 62 30", 16, 4, 101, "hoteldesvosges.png", strasbourg));
		hotelServiceImpl.saveHotel(new Hotel(null, "OKKO ", "13 Rue d'Amiens, 59800 Lille", "03 20 48 19 40", 32, 4, 90, "okko.png", lille));
		hotelServiceImpl.saveHotel(new Hotel(null, "Hôtel l'Arbre Voyageur", "45 Bd Carnot, 59800 Lille", "03 20 20 62 62", 30, 4, 107, "l'arbre.png", lille));
		hotelServiceImpl.saveHotel(new Hotel(null, "Le Napoléon", "17 Pl. de la Gare, 59800 Lille", "03 20 42 19 69", 16, 3, 34, "lenapoleon.png", lille));
	}
	/*private void generateUsersRoles() {
		accountService.saveUser(new AppUser(null,"mohamed","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"rory","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"raph","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"guigui","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"ayyoub","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"benoit","1234",new ArrayList<>()));
		accountService.saveUser(new AppUser(null,"alex","1234",new ArrayList<>()));
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"MANAGER"));
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.addRoleToUser("mohamed","ADMIN");
		accountService.addRoleToUser("mohamed","USER");
		accountService.addRoleToUser("rory","MANAGER");
		accountService.addRoleToUser("rory","USER");
		accountService.addRoleToUser("raph","MANAGER");
		accountService.addRoleToUser("raph","USER");
		accountService.addRoleToUser("ayyoub","MANAGER");
		accountService.addRoleToUser("ayyoub","USER");
		accountService.addRoleToUser("guigui","MANAGER");
		accountService.addRoleToUser("guigui","USER");
		accountService.addRoleToUser("benoit","USER");
		accountService.addRoleToUser("alex","USER");
	}*/
}
