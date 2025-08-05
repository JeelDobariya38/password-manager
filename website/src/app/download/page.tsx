"use client";

import Link from "next/link";
import { useEffect, useState } from "react";

const GithubLatestReleaseApiURL =
  "https://api.github.com/repos/JeelDobariya38/Passcodes/releases/latest";

export type Asset = {
  name: string;
  content_type: string;
  size: Number;
  created_at: string;
  download_count: Number;
  browser_download_url: string;
};

export type LatestRelease = {
  name: string;
  body: string;
  html_url: string;
  published_at: string;
  prerelease: boolean;
  assets: Asset[];
};

export default function page() {
  let [latestRelease, setLatestRelease] = useState<LatestRelease>();

  useEffect(() => {
    async function getLatestRelease() {
      let res = await fetch(GithubLatestReleaseApiURL);

      if (res.ok) {
        setLatestRelease(await res.json());
        return;
      }
    }

    getLatestRelease();
  }, []);

  if (!latestRelease) {
    return "404: No Latest Release Found!!";
  }

  console.log(latestRelease);

  const universalApkAsset = latestRelease.assets.find((asset) =>
    asset.name.includes("universal-release.apk")
  );

  if (!universalApkAsset) {
    throw new Error(
      `404: No universial apk asset found for ${latestRelease.name}`
    );
  }

  return (
    <div className="bg-cyan-900 text-black px-2 py-4 rounded">
      <h1 className="text-white text-2xl pb-4">{latestRelease.name}</h1>
      {!latestRelease.prerelease && (
        <div className="inline-block bg-black rounded text-red-500 p-2">
          PreRelease
        </div>
      )}
      <div className="bg-cyan-950 text-gray-500 rounded p-4">
        <p className="line-clamp-5 whitespace-break-spaces text-balance">
          {latestRelease.body}
        </p>
      </div>

      <div className="p-2 text-lg">
        <p className="">
          Downloads: {universalApkAsset.download_count.toString()}
        </p>
        <p className="">Published At: {latestRelease.published_at}</p>
      </div>

      <div className="py-2 flex gap-2">
        <Link
          className="inline-block p-2 rounded-3xl bg-red-300"
          href={universalApkAsset.browser_download_url}
          download={true}
        >
          Download Apk
        </Link>
        <Link
          className="inline-block p-2 rounded-3xl bg-red-300"
          href={latestRelease.html_url}
          download={true}
        >
          View Release
        </Link>
      </div>
    </div>
  );
}
