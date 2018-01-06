package com.andigeeky.weddinginvitation.storage.upload;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.andigeeky.weddinginvitation.domain.service.networking.common.Resource;
import com.andigeeky.weddinginvitation.domain.service.networking.common.Status;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ImageViewModel extends ViewModel {
    private UploadImageUseCase uploadImageUseCase;
    private MediatorLiveData<UploadImageResponse> mediatorLiveData = new MediatorLiveData<>();
    private ArrayList<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;

    ImageViewModel(UploadImageUseCase uploadImageUseCase) {
        this.uploadImageUseCase = uploadImageUseCase;
    }

    public void uploadImages(ArrayList<Image> imageList) {
            images.addAll(imageList);
            startUpload();
    }

    private void startUpload() {
        LiveData<Resource<UploadTask.TaskSnapshot>> resourceLiveData = uploadImageUseCase.uploadImages(images.get(currentImageIndex).getAbsolutePath());

        this.mediatorLiveData.addSource(resourceLiveData, taskSnapshotResource -> {
            if ((taskSnapshotResource != null ? taskSnapshotResource.status : null) == Status.SUCCESS) {
                images.get(currentImageIndex).setState(State.SUCCESS);
            } else if ((taskSnapshotResource != null ? taskSnapshotResource.status : null) == Status.ERROR) {
                images.get(currentImageIndex).setState(State.ERROR);
            }

            UploadImageResponse uploadImageResponse = new UploadImageResponse();
            uploadImageResponse.setCurrentImageIndex(currentImageIndex);
            uploadImageResponse.setResult(taskSnapshotResource);
            uploadImageResponse.setImage(images.get(currentImageIndex));
            mediatorLiveData.setValue(uploadImageResponse);


            if ((taskSnapshotResource != null ? taskSnapshotResource.status : null) != Status.LOADING) {
                currentImageIndex++;
                if (currentImageIndex < images.size()) {
                    mediatorLiveData.removeSource(resourceLiveData);
                    startUpload();
                }
            }
        });
    }

    public LiveData<UploadImageResponse> getImages() {
        return mediatorLiveData;
    }

    public void clearData() {
        images.clear();
        currentImageIndex = 0;
    }
}
