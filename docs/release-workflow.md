# Release Workflow

In this file, you will find documented process about, how we release, new version of a app.

1. We follow, so called `Main as Production Environment`. So, during the time of release we test the main branch code.

2. Updated the Code where ever need. (code that refer to previous version).

3. Make Documentation up-to-date. (document the main change in app from user point of view).

4. Update `changelog.md`.

5. Draft a new release with all apk files uploaded (signed apks).

6. Discuss on community and check & test release app on various parameters and
   - We run unit tests.
   - We run android tests.
   - And ofcourse, we internally as developers also test it (manually).

7. Then release it.
