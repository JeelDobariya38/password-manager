type Asset = {
  url: string;
  id: number;
  name: string;
  download_count: number;
  browser_download_url: string;
};

type Release = {
  html_url: string;
  id: number;
  tag_name: string;
  prerelease: boolean;
  created_at: string;
  published_at: string;
  assets: Asset[];
};

export async function getLatestDownloadableUniversialApkUrl() {
  try {
    let res = await fetch(
      "https://api.github.com/repos/JeelDobariya38/Passcodes/releases/latest"
    );
    let latestRelease: Release = await res.json();
    let assets = latestRelease.assets;
    let universalApkAssets = assets.find((asset) =>
      asset.name.includes("universal")
    );
    if (!universalApkAssets) throw new Error("No Universal Apk Found!!");
    return universalApkAssets.browser_download_url;
  } catch (e) {
    throw new Error("Can't find latest release!!");
  }
}
