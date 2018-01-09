package com.andigeeky.weddinginvitation.di;

import com.andigeeky.weddinginvitation.common.FacebookLoginHelper;
import com.andigeeky.weddinginvitation.common.GoogleLoginHelper;
import com.andigeeky.weddinginvitation.domain.RegisterUseCase;
import com.andigeeky.weddinginvitation.repository.RemoteRepository;
import com.andigeeky.weddinginvitation.view.SignUpScreen;

import dagger.Module;
import dagger.Provides;

/**
 * Define CommentsActivity-specific dependencies here.
 */
@Module
public class SignUpScreenModule {
    @Provides
    GoogleLoginHelper provideGoogleLoginHelper(SignUpScreen activity) {
        return new GoogleLoginHelper(activity);
    }

    @Provides
    FacebookLoginHelper provideFacebookLoginHelper(SignUpScreen activity) {
        return new FacebookLoginHelper(activity);
    }
}
