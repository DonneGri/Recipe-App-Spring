package recipe.springframework.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import recipe.springframework.domain.Recipe;
import recipe.springframework.repositories.RecipeRepository;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		log.debug("Received a file");
		
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			/*
			 * need to convert because multi-part file is primitive byte array.
			 *  Need to convert it to the wrapper class of a byte.
			 */
			Byte [] byteObjects = new Byte[file.getBytes().length];
			
			int i = 0;
			
			//convert primitive byte to Byte
			for(byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			recipe.setImage(byteObjects);
			
			recipeRepository.save(recipe);
		}catch (IOException e) {
			log.error("Error occurred",e);
			
			e.printStackTrace();
		}

	}

}
