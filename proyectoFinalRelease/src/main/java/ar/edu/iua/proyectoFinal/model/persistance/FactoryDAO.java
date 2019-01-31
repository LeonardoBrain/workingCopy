package ar.edu.iua.proyectoFinal.model.persistance;

import org.springframework.context.annotation.Bean;

public class FactoryDAO {

	private static FactoryDAO instance;


	@Bean
	public static FactoryDAO getInstance() {
		if (instance == null) {
			instance = new FactoryDAO();
		}
		return instance;

	}

	private static String dataBaseActive = "MYSQL";

	public static IGenericDAO getTaskDAO() {

		if (dataBaseActive == "MYSQL") {
			return TaskDAO.getInstance();

		} /*
			 * else { return BillingOracleImplDAO.getInstance(); }
			 */

		return null;
	}



}
