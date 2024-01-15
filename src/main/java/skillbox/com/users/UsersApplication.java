package skillbox.com.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import skillbox.com.users.entity.CityEntity;
import skillbox.com.users.entity.SubscriptionEntity;
import skillbox.com.users.entity.UserEntity;
import skillbox.com.users.repository.CityRepository;
import skillbox.com.users.repository.SubscriptionRepository;
import skillbox.com.users.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner demoJpa(CityRepository cityRepository,
							  UserRepository userRepository,
							  SubscriptionRepository subscriptionRepository) {
		return (args) -> {

			// ==== ПРИМЕР: создаем объекты с городами: Москва, Липецк, Воронеж ====
			/*
			CityEntity moscow  = new CityEntity("Москва");
			CityEntity lipetsk = new CityEntity("Липецк");
			CityEntity voroneg = new CityEntity("Воронеж");

			// сохраняем в базу города
			cityRepository.save(moscow);
			cityRepository.save(lipetsk);
			cityRepository.save(voroneg);
			*/

			System.out.println("===== Вывод списка всех городов из базы =====");
			cityRepository.findAll().forEach(System.out::println);

            // ==== ПРИМЕР: создаем объекты с пользователями Олег и Виктор ====

			// Олег
			/*
			UserEntity oleg = new UserEntity();
			oleg.setName("Олег");
			oleg.setLogin("111");
			oleg.setGender("M"); // Male
			oleg.setEmail("oleg@gmail.com");
			oleg.setPhone("8(950)111-11-11");
			oleg.setActive(true);
			oleg.setAddress("Адрес, где проживает Олег");

			// привязка пользователя Олега к городу Липецк
			oleg.setCityEntity(lipetsk);

			// Сохраняем пользователя Олега в базе
			userRepository.save(oleg);
			*/

			// Виктор
			/*
			UserEntity victor = new UserEntity();
			victor.setName("Виктор");
			victor.setLogin("222");
			victor.setGender("M"); // Male
			victor.setEmail("victor@gmail.com");
			victor.setPhone("8(960)222-22-22");
			victor.setActive(true);
			victor.setAddress("Адрес, где проживает Виктор");

			// привязка пользователя Игоря к городу Москва
			victor.setCityEntity(moscow);

			// сохраняем пользователя Виктора в базе
			userRepository.save(victor);
			*/

			// ПРИМЕР: Поиск города по ИД и привязка к нему нового пользователя Дмитрия
			Optional<CityEntity> city = cityRepository.findById(15);

			// можно осуществлять поиск по наименованмию города (TODO: нужен Уникальный индекс по городу !!!):
			// List<CityEntity> lipetskList = cityRepository.findAllByName("Липецк");
			// или по вхождению, но тогда результат может содержать несколько найденных городов
			// List<CityEntity> lipetskList = cityRepository.findAllByNameLike("Липе%");
			// lipetskList.forEach(System.out::println);

			if (city.isPresent()) {
				System.out.println(city);

				/*
				Создаем пользователя Дмитрия и привязываем его к найденному городу

				UserEntity dmitry = new UserEntity();
				dmitry.setName("Дмитрий");
				dmitry.setLogin("333");
				dmitry.setGender("M"); // Male
				dmitry.setEmail("dmitry@gmail.com");
				dmitry.setPhone("8(960)333-33-33");
				dmitry.setActive(true);
				dmitry.setAddress("Адрес, где проживает Дмитрий");
				dmitry.setCityEntity(city);

				userRepository.save(dmitry);
				*/

				// Код ниже не работает при fetch = FetchType.LAZY в классе CityEntity
				// при FetchType.EAGER - работает
				// поэтому в репозитории реализовал метод findUsersByCityId
				List<UserEntity> users = city.get().getUserEntityList();
				users.forEach(System.out::println);

                // вывод списка пользователей по городу с Ид = 15
				List<UserEntity> userList = userRepository.findUsersByCityId(city.get().getId());
				userList.forEach(System.out::println);

			}

			// Пример: Олег подписывается на Виктора
			Optional<UserEntity> oleg = userRepository.findByLogin("oleg");
			// oleg.ifPresent(userEntity -> System.out.println(userEntity.getName()));

			Optional<UserEntity> victor = userRepository.findByLogin("victor");
			// victor.ifPresent(userEntity -> System.out.println(userEntity.getName()));

			// если обоих нашли - осуществляем подписку
			if (oleg.isPresent() && victor.isPresent()) {
				//SubscriptionEntity subscription = new SubscriptionEntity(Date.valueOf(LocalDate.now()), oleg.get(), victor.get());
				//subscriptionRepository.save(subscription);

				// теперь Олег отписывается от Виктора
				// Алгоритм: нужно найти пользователя Олега в качестве подписчика и Виктора в качетве подписуемого (реализовано выше),
				// найти запись в таблице subscription по ИД подписчика и ИД подписуемого и удалить ее

				Optional<SubscriptionEntity> subscription_1 = subscriptionRepository.findBySubscriberIdAndSubscribedId(oleg.get().getId(), victor.get().getId());
                subscription_1.ifPresent(subscriptionEntity -> subscriptionRepository.deleteById(subscriptionEntity.getId()));

			}

		};
	}

}
