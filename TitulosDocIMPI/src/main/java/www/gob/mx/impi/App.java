package www.gob.mx.impi;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		App obj = new App();
		obj.run();
	}

	private void run() {

		String[] springConfig = { "job-extract-docs.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("jobProcesaDocs");

		try {

			JobParameters param = new JobParametersBuilder().addString("ID", "1").toJobParameters();
			// JobParameters param = new
			// JobParametersBuilder().addString("name","user_c").toJobParameters();

			JobExecution execution = jobLauncher.run(job, param);
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : " + execution.getAllFailureExceptions());

			for (Throwable ex : execution.getAllFailureExceptions()){
				if (ex instanceof OfficeXmlFileException){
					//
					System.out.println("Se está leyendo un archivo docx");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		System.out.println("Done");

	}

}
