package org.example.studyregistry;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyMaterialTest {
    static StudyMaterial studyMaterial;

    @BeforeAll
    static void setUpBeforeAll() {
        studyMaterial = StudyMaterial.getStudyMaterial();
    }

    @Test
    @Order(1)
    @DisplayName("Add Reference Test")
    void addReferenceTest() {
        AudioReference audioReference = addAudioReference();
        TextReference textReference = addTextReference();
        VideoReference videoReference = addVideoReference();

        List<Reference> references = studyMaterial.getReferences();
        assertTrue(references.contains(audioReference));
        assertTrue(references.contains(textReference));
        assertTrue(references.contains(videoReference));
    }

    AudioReference addAudioReference(){
        AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.HIGH);
        audioReference.editBasic("Audio 1", "Audio 1 Description", "link");
        studyMaterial.addReference(audioReference);
        return audioReference;
    }

    TextReference addTextReference(){
        TextReference textReference = new TextReference("Test", "English", 500, "pdf", "Open");
        textReference.editAccess("Public", "pdf", 300);
        studyMaterial.addReference(textReference);
        return textReference;
    }

    VideoReference addVideoReference(){
        VideoReference videoReference = new VideoReference("How to Test", "Video about how to test");
        videoReference.editAvailability(true, true);
        studyMaterial.addReference(videoReference);
        return videoReference;
    }

    @Test
    @Order(2)
    @DisplayName("Get Reference Count Map Test")
    void getReferenceCountMap() {
        addAudioReference();
        addTextReference();
        addVideoReference();
        Map<String, Integer> referenceCountMap = studyMaterial.getReferenceCountMap();
        int audio = referenceCountMap.get("Audio References");
        int video = referenceCountMap.get("Video References");
        int text = referenceCountMap.get("Text References");
        assertTrue(audio >= 1);
        assertTrue(video >= 1);
        assertTrue(text >= 1);
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
    @Order(3)
    @DisplayName("Search Audio Reference Test")
    void searchAudioReferenceTest() {
        AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.HIGH);
        audioReference.editBasic("Search Audio Test", "Search Audio Description Test", "Search Audio Link Test");
        studyMaterial.addReference(audioReference);
        List<String> response = studyMaterial.searchInMaterials("Search Audio");
        assertTrue(verifySearchResponse("Search Audio Test", response));
    }

    @Test
    @Order(4)
    @DisplayName("Search Video Reference Test")
    void searchVideoReferenceTest() {
        VideoReference videoReference = new VideoReference("How to test Video References", "Video about how to test");
        studyMaterial.addReference(videoReference);
        List<String> response = studyMaterial.searchInMaterials("test Video References");
        assertTrue(verifySearchResponse("test Video References", response));
    }

    @Test
    @Order(5)
    @DisplayName("Search Text Reference Test")
    void searchTextReferenceTest() {
        TextReference textReference = new TextReference("Test Search Text Reference", "English", 500, "md", "Closed");
        studyMaterial.addReference(textReference);
        List<String> response = studyMaterial.searchInMaterials("Search Text Reference");
        assertTrue(verifySearchResponse("Search Text Reference", response));
    }
}