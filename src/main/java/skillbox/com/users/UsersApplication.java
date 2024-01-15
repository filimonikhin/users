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

			// ==== ������: ������� ������� � ��������: ������, ������, ������� ====
			/*
			CityEntity moscow  = new CityEntity("������");
			CityEntity lipetsk = new CityEntity("������");
			CityEntity voroneg = new CityEntity("�������");

			// ��������� � ���� ������
			cityRepository.save(moscow);
			cityRepository.save(lipetsk);
			cityRepository.save(voroneg);
			*/

			System.out.println("===== ����� ������ ���� ������� �� ���� =====");
			cityRepository.findAll().forEach(System.out::println);

            // ==== ������: ������� ������� � �������������� ���� � ������ ====

			// ����
			/*
			UserEntity oleg = new UserEntity();
			oleg.setName("����");
			oleg.setLogin("111");
			oleg.setGender("M"); // Male
			oleg.setEmail("oleg@gmail.com");
			oleg.setPhone("8(950)111-11-11");
			oleg.setActive(true);
			oleg.setAddress("�����, ��� ��������� ����");

			// �������� ������������ ����� � ������ ������
			oleg.setCityEntity(lipetsk);

			// ��������� ������������ ����� � ����
			userRepository.save(oleg);
			*/

			// ������
			/*
			UserEntity victor = new UserEntity();
			victor.setName("������");
			victor.setLogin("222");
			victor.setGender("M"); // Male
			victor.setEmail("victor@gmail.com");
			victor.setPhone("8(960)222-22-22");
			victor.setActive(true);
			victor.setAddress("�����, ��� ��������� ������");

			// �������� ������������ ����� � ������ ������
			victor.setCityEntity(moscow);

			// ��������� ������������ ������� � ����
			userRepository.save(victor);
			*/

			// ������: ����� ������ �� �� � �������� � ���� ������ ������������ �������
			Optional<CityEntity> city = cityRepository.findById(15);

			// ����� ������������ ����� �� ������������� ������ (TODO: ����� ���������� ������ �� ������ !!!):
			// List<CityEntity> lipetskList = cityRepository.findAllByName("������");
			// ��� �� ���������, �� ����� ��������� ����� ��������� ��������� ��������� �������
			// List<CityEntity> lipetskList = cityRepository.findAllByNameLike("����%");
			// lipetskList.forEach(System.out::println);

			if (city.isPresent()) {
				System.out.println(city);

				/*
				������� ������������ ������� � ����������� ��� � ���������� ������

				UserEntity dmitry = new UserEntity();
				dmitry.setName("�������");
				dmitry.setLogin("333");
				dmitry.setGender("M"); // Male
				dmitry.setEmail("dmitry@gmail.com");
				dmitry.setPhone("8(960)333-33-33");
				dmitry.setActive(true);
				dmitry.setAddress("�����, ��� ��������� �������");
				dmitry.setCityEntity(city);

				userRepository.save(dmitry);
				*/

				// ��� ���� �� �������� ��� fetch = FetchType.LAZY � ������ CityEntity
				// ��� FetchType.EAGER - ��������
				// ������� � ����������� ���������� ����� findUsersByCityId
				List<UserEntity> users = city.get().getUserEntityList();
				users.forEach(System.out::println);

                // ����� ������ ������������� �� ������ � �� = 15
				List<UserEntity> userList = userRepository.findUsersByCityId(city.get().getId());
				userList.forEach(System.out::println);

			}

			// ������: ���� ������������� �� �������
			Optional<UserEntity> oleg = userRepository.findByLogin("oleg");
			// oleg.ifPresent(userEntity -> System.out.println(userEntity.getName()));

			Optional<UserEntity> victor = userRepository.findByLogin("victor");
			// victor.ifPresent(userEntity -> System.out.println(userEntity.getName()));

			// ���� ����� ����� - ������������ ��������
			if (oleg.isPresent() && victor.isPresent()) {
				//SubscriptionEntity subscription = new SubscriptionEntity(Date.valueOf(LocalDate.now()), oleg.get(), victor.get());
				//subscriptionRepository.save(subscription);

				// ������ ���� ������������ �� �������
				// ��������: ����� ����� ������������ ����� � �������� ���������� � ������� � ������� ������������ (����������� ����),
				// ����� ������ � ������� subscription �� �� ���������� � �� ������������ � ������� ��

				Optional<SubscriptionEntity> subscription_1 = subscriptionRepository.findBySubscriberIdAndSubscribedId(oleg.get().getId(), victor.get().getId());
                subscription_1.ifPresent(subscriptionEntity -> subscriptionRepository.deleteById(subscriptionEntity.getId()));

			}

		};
	}

}
