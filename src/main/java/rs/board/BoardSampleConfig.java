package rs.board;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@ComponentScan(basePackages= {"rs.board"})
@PropertySource("classpath:application.properties")
public class BoardSampleConfig {
	
	@Value("${spring.redis.host}")
	private String redisHost;
	
	@Value("${spring.redis.port}")
	private int redisPort;
	
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		
		RedisStandaloneConfiguration redisStandaloneConfiguration  = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHost);
		redisStandaloneConfiguration.setPort(redisPort);
		
		JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
        jedisClientConfigurationBuilder.connectTimeout(Duration.ofSeconds(60));

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfigurationBuilder.build());
		
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String,Object> resisTemplate(){
		RedisTemplate<String,Object> template =  new RedisTemplate<String,Object>();
		
		template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new StringRedisSerializer() );
        template.setHashKeySerializer( new StringRedisSerializer() );
        template.setValueSerializer( new StringRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory());
       
		return template;
	
	}

}
