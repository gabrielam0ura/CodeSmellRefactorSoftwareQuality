package org.example.studysearch;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.StudyMaterial;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MaterialSearchTest {
    static MaterialSearch materialSearch = new MaterialSearch();
    static StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();

    @BeforeAll
    static void setUp() {
    }

    static void addAudioReference(){
        AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.LOW);
        audioReference.editBasic("MaterialSearchTestAudio Test", "Test", "Test");
        AudioReference audioReference2 = new AudioReference(AudioReference.AudioQuality.MEDIUM);
        audioReference2.editBasic("Test", "MaterialSearchTestAudio2 Test", "Test");
        AudioReference audioReference3 = new AudioReference(AudioReference.AudioQuality.HIGH);
        audioReference3.editBasic("Test", "Test", "MaterialSearchTestAudio3 Test");
        studyMaterial.addReference(audioReference);
        studyMaterial.addReference(audioReference2);
        studyMaterial.addReference(audioReference3);
    }

    static void addVideoReference(){
        VideoReference videoReference = new VideoReference("MaterialSearchTestVideo Test", "Test");
        VideoReference videoReference2 = new VideoReference("Test", "MaterialSearchTestVideo2 Test");
        studyMaterial.addReference(videoReference);
        studyMaterial.addReference(videoReference2);
    }

    static void addTextReference(){
        TextReference textReference = new TextReference("MaterialSearchTestText Test", "English", 200, "pdf", "Closed");
        studyMaterial.addReference(textReference);
    }

    boolean verifySearchResponse(String searching, List<String> response) {
        for(String object : response){
            if(object.contains(searching)){
                return true;
            }
        }
        return false;
    }


    @Test
    @Order(1)
    @DisplayName("Material Search Audio Reference Test")
    void materialSearchAudioReferenceTest() {
        addAudioReference();
        List<String> response = materialSearch.search("SearchTestAudio");
        assertTrue(verifySearchResponse("MaterialSearchTestAudio Test", response));
        assertFalse(verifySearchResponse("MaterialSearchTestAudio2 Test", response));
        assertFalse(verifySearchResponse("MaterialSearchTestAudio3 Test", response));
    }

    @Test
    @Order(2)
    @DisplayName("Material Search Video Reference Test")
    void materialSearchVideoReferenceTest() {
        addVideoReference();
        List<String> response = materialSearch.search("SearchTestVideo");
        assertTrue(verifySearchResponse("MaterialSearchTestVideo Test", response));
        assertFalse(verifySearchResponse("MaterialSearchTestVideo2 Test", response));
    }

    @Test
    @Order(3)
    @DisplayName("Material Search Text Reference Test")
    void materialSearchTextReferenceTest() {
        addTextReference();
        List<String> response = materialSearch.search("SearchTestText");
        assertTrue(verifySearchResponse("MaterialSearchTestText Test", response));
    }
}