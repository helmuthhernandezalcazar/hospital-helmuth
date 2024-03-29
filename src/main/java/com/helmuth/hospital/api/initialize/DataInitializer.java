package com.helmuth.hospital.api.initialize;

import com.helmuth.hospital.api.entity.*;
import com.helmuth.hospital.api.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BuildingBlockRepository buildingBlockRepository;
    @Autowired
    private MedicalSpecialtyRepository medicalSpecialtyRepository;
    @Autowired
    private MedicalFloorRepository medicalFloorRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private MeasurementTypeRepository measurementTypeRepository;
    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private TriageRepository triageRepository;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final int NUMBER_OF_PATIENTS = 16;
    private final int NUMBER_OF_EMPLOYEES = 8;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));

        UserDetails user1 = User
                .withUsername("helmuth")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails user2 = User
                .withUsername("david")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails user3 = User
                .withUsername("hernandez")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();


        List<UserDetails> users = List.of(user1, user2, user3);
        users.forEach(user -> userDetailsManager.createUser(user));


        MeasurementType bloodPressure = MeasurementType.builder().measurementType("Presión sanguinea").unit("mmHg").build();
        MeasurementType temperature = MeasurementType.builder().measurementType("Temperatura").unit("ºC").build();
        MeasurementType weight = MeasurementType.builder().measurementType("Peso").unit("kg").build();
        List<MeasurementType> measurementTypes = new ArrayList<>(List.of(bloodPressure,temperature, weight));
        measurementTypeRepository.saveAll(measurementTypes);

        Triage triage1 = Triage.builder().level(1).name("Atención inmediata").timeToBeAttended("0 minutos").description("Corre peligro de perder la vida")
                .examples("paro cardíaco, dificultad respiratoria aguda, traumatismo severo").build();
        Triage triage2 = Triage.builder().level(2).name("Muy urgente").timeToBeAttended("< 15 minutos").description("Situación de alto riesgo")
                .examples("quemaduras, hemorragias, exposición a tóxicos, letargia, dolor abdominal agudo, consufión").build();
        Triage triage3 = Triage.builder().level(3).name("Urgente").timeToBeAttended("15-60 minutos").description("No hay riesgo inminente pero presenta alteración aguda en el estado de salud")
                .examples("fiebre, vómito y diarrea con deshidratación, traumas menores").build();
        Triage triage4 = Triage.builder().level(4).name("Urgencia menor").timeToBeAttended("60-120 minutos").description("Requiere de exámenes o consultas para iniciar tratamiento o cambio por evolución en el estado de salud previo")
                .examples("infección urinaria o viral sin mejoría, paciente post operatorio de más de 1 día que pueda presentar alguna complicación").build();
        Triage triage5 = Triage.builder().level(5).name("No urgente").timeToBeAttended("120-240 minutos").description("No requiere atención en el servicio de urgencias, puede ser atendido")
                .examples("resfriado, congestión, dolor leve, enfermedad crónica").build();
        List<Triage> triages = new ArrayList<>(List.of(triage1, triage2, triage3, triage4, triage5));
        triageRepository.saveAll(triages);


        MedicalSpecialty cardiology = MedicalSpecialty.builder().name("Cardiología")
                .description("Departamento especializado en el cuidado y tratamiento de enfermedades del corazón. Se brinda atención médica a pacientes con afecciones cardiovasculares, desde problemas menores hasta condiciones graves").build();
        MedicalSpecialty pulmonology = MedicalSpecialty.builder().name("Neumología")
                .description("Departamento médico especializado en el diagnóstico y tratamiento de enfermedades respiratorias. Se brinda atención médica a pacientes con afecciones como el asma, la enfermedad pulmonar obstructiva crónica (EPOC), la neumonía, la fibrosis pulmonar y otras enfermedades pulmonares y respiratorias.").build();
        MedicalSpecialty urology = MedicalSpecialty.builder().name("Urología")
                .description("Departamento médico especializado en el diagnóstico y tratamiento de trastornos del sistema urinario en hombres y mujeres, así como de enfermedades del sistema reproductor masculino. " +
                        "Se brinda atención médica integral para abordar condiciones como infecciones del tracto urinario, cálculos renales, enfermedades de la próstata, disfunciones sexuales, enfermedades de transmisión sexual y cánceres urológicos").build();
        MedicalSpecialty neurology = MedicalSpecialty.builder().name("Neurología")
                .description("Departamento médico especializado en el diagnóstico y tratamiento de trastornos del sistema nervioso. Se brinda atención médica a pacientes con afecciones neurológicas como enfermedades cerebrovasculares, trastornos del movimiento, epilepsia, enfermedades neurodegenerativas, migrañas, trastornos del sueño y lesiones en el sistema nervioso").build();
        medicalSpecialtyRepository.saveAll(List.of(cardiology, pulmonology, urology, neurology));

        BuildingBlock buildingBlockA = BuildingBlock.builder().name("A").address(faker.address().streetAddress()).city(faker.address().city()).numberOfFloors(3).build();
        BuildingBlock buildingBlockB = BuildingBlock.builder().name("B").address(faker.address().streetAddress()).city(faker.address().city()).numberOfFloors(3).build();
        buildingBlockRepository.saveAll(List.of(buildingBlockA, buildingBlockB));

        MedicalFloor medicalFloor1A = MedicalFloor.builder().name("1A").floor(1).buildingBlock(buildingBlockA).medicalSpecialty(cardiology).build();
        MedicalFloor medicalFloor2A = MedicalFloor.builder().name("2A").floor(2).buildingBlock(buildingBlockA).medicalSpecialty(pulmonology).build();
        MedicalFloor medicalFloor1B = MedicalFloor.builder().name("1B").floor(1).buildingBlock(buildingBlockB).medicalSpecialty(urology).build();
        MedicalFloor medicalFloor2B = MedicalFloor.builder().name("2B").floor(2).buildingBlock(buildingBlockB).medicalSpecialty(neurology).build();
        medicalFloorRepository.saveAll(List.of(medicalFloor1A, medicalFloor2A, medicalFloor1B, medicalFloor2B));

        Room room101 = Room.builder().name("101").medicalFloor(medicalFloor1A).build();
        Room room102 = Room.builder().name("102").medicalFloor(medicalFloor1A).build();
        Room room103 = Room.builder().name("103").medicalFloor(medicalFloor1A).build();
        Room room104 = Room.builder().name("104").medicalFloor(medicalFloor1A).build();
        Room room105 = Room.builder().name("105").medicalFloor(medicalFloor1A).build();
        Room room106 = Room.builder().name("106").medicalFloor(medicalFloor1A).build();

        Room room201 = Room.builder().name("201").medicalFloor(medicalFloor2A).build();
        Room room202 = Room.builder().name("202").medicalFloor(medicalFloor2A).build();
        Room room203 = Room.builder().name("203").medicalFloor(medicalFloor2A).build();
        Room room204 = Room.builder().name("204").medicalFloor(medicalFloor2A).build();
        Room room205 = Room.builder().name("205").medicalFloor(medicalFloor2A).build();
        Room room206 = Room.builder().name("206").medicalFloor(medicalFloor2A).build();

        Room room107 = Room.builder().name("107").medicalFloor(medicalFloor1B).build();
        Room room108 = Room.builder().name("108").medicalFloor(medicalFloor1B).build();
        Room room109 = Room.builder().name("109").medicalFloor(medicalFloor1B).build();
        Room room110 = Room.builder().name("110").medicalFloor(medicalFloor1B).build();
        Room room111 = Room.builder().name("111").medicalFloor(medicalFloor1B).build();
        Room room112 = Room.builder().name("112").medicalFloor(medicalFloor1B).build();

        Room room207 = Room.builder().name("207").medicalFloor(medicalFloor2B).build();
        Room room208 = Room.builder().name("208").medicalFloor(medicalFloor2B).build();
        Room room209 = Room.builder().name("209").medicalFloor(medicalFloor2B).build();
        Room room210 = Room.builder().name("210").medicalFloor(medicalFloor2B).build();
        Room room211 = Room.builder().name("211").medicalFloor(medicalFloor2B).build();
        Room room212 = Room.builder().name("212").medicalFloor(medicalFloor2B).build();
        List<Room> rooms = List.of(room101, room102, room103, room104,
                room201, room202, room203, room204,
                room105, room106, room107, room108,room109, room110, room111, room112,
                room205, room206, room207, room208, room209, room210, room211, room212);
        roomRepository.saveAll(rooms);


        List<String> names = faker
                .collection(() -> faker.name().firstName())
                .len(NUMBER_OF_PATIENTS)
                .generate();
        List<String> lastNames = faker
                .collection(() -> faker.name().lastName())
                .len(NUMBER_OF_PATIENTS)
                .generate();

        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            Date registerDate = faker.date().past(365, TimeUnit.DAYS);
            Date dischargeDate = faker.date().past(365, TimeUnit.DAYS);
            Patient patient = Patient.builder()
                    .firstName(names.get(i))
                    .lastName(lastNames.get(i))
                    .dni(faker.expression("#{bothify '########?', 'true'}"))
                    .phoneNumber(faker.expression("#{numerify '6## ### ###'}"))
                    .registerDate(registerDate)
                    .dischargeDate(dischargeDate.getTime() > registerDate.getTime() ? dischargeDate : null)
                    .discharged(dischargeDate.getTime() > registerDate.getTime())
                    .symptoms(faker.medical().symptoms())
                    .medicalDiagnosis(faker.medical().diseaseName())
                    .room(dischargeDate.getTime() > registerDate.getTime() ? null : rooms.get(i))
                    .triage(triages.get((int) (Math.random() * (triages.size() - 1))))
                    .build();
            patients.add(patient);
        }
        patientRepository.saveAll(patients);


        List<String> employeeNames = faker
                .collection(() -> faker.name().firstName())
                .len(NUMBER_OF_EMPLOYEES)
                .generate();
        List<String> employeeLastNames = faker
                .collection(() -> faker.name().lastName())
                .len(NUMBER_OF_EMPLOYEES)
                .generate();

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
            Employee employee = Employee.builder()
                    .firstName(employeeNames.get(i))
                    .lastName(employeeLastNames.get(i))
                    .dni(faker.expression("#{bothify '########?', 'true'}"))
                    .phoneNumber(faker.expression("#{numerify '6## ### ###'}"))
                    .email((employeeNames.get(i) + "." + employeeLastNames.get(i)).toLowerCase().trim() + "@hospitalalcazar.com")
                    .build();
            employees.add(employee);
        }
        employeeRepository.saveAll(employees);

        //for each employee an user is created
        employees.forEach(employee -> {
            UserDetails userDetails = User.withUsername(employee.getEmail())
                    .roles("USER")
                    .password(passwordEncoder.encode("password"))
                    .build();
            userDetailsManager.createUser(userDetails);
        });

        List<Note> notes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Note note = Note.builder()
                    .note(faker.lorem().characters(20, 250))
                    .date(faker.date().past(180, TimeUnit.DAYS))
                    .patient(patients.get((int) (Math.random() * (patients.size() - 1))))
                    .employee(employees.get((int) (Math.random() * (employees.size() - 1))))
                    .build();
            notes.add(note);
        }
        noteRepository.saveAll(notes);

        List<Measurement> measurements = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            Measurement measurement = Measurement.builder()
                    .measurementValue(String.valueOf(Math.random() * 100))
                    .date(faker.date().past(180, TimeUnit.DAYS))
                    .measurementType(measurementTypes.get((int) (Math.random() * (measurementTypes.size() - 1))))
                    .patient(patients.get((int) (Math.random() * (patients.size() - 1))))
                    .employee(employees.get((int) (Math.random() * (employees.size() - 1))))
                    .build();
            measurements.add(measurement);
        }
        measurementRepository.saveAll(measurements);
    }

}
