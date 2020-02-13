package fr.epsi.atlas.monnaie.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import fr.epsi.atlas.monnaie.repository.MonnaieRepository;

public class MonnaieServiceTest {
	
	@Test
	public void deletebyCode_quandLaMonnaieExiste() throws Exception {
		MonnaieRepository mockRepository = Mockito.mock(MonnaieRepository.class);
		Mockito.when(mockRepository.existsById("USD")).thenReturn(true);

		MonnaieService sut = new MonnaieService(mockRepository);
		sut.deleteByCode("USD");

		Mockito.verify(mockRepository).deleteById("USD");
}
	@Test(expected = MonnaieInexistanteException.class)
	public void getByCode_lanceUneExceptionLorsqueLeCodeNeCorrespondPasAUneMonnaie() throws Exception {
		MonnaieRepository mockRepository = Mockito.mock(MonnaieRepository.class);
		MonnaieService sut = new MonnaieService(mockRepository);
		when(mockRepository.findById("USD")).thenReturn(Optional.empty());
		
		sut.getByCode("USD");
	}
}
