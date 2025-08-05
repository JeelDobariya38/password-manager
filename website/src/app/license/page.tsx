"use client";

import { redirectToUrl } from "@/libs/utils";
import { useEffect, useState } from "react";

export const LicenseHtmlURL =
  "https://github.com/JeelDobariya38/Passcodes/blob/main/LICENSE.txt";
export const LicenseAPIURL =
  "https://raw.githubusercontent.com/JeelDobariya38/Passcodes/main/LICENSE.txt";

export default function page() {
  let [licenseContent, setLicenseContent] = useState<String>("");

  useEffect(() => {
    async function getLicenseContent() {
      let res = await fetch(LicenseAPIURL);

      if (res.ok) {
        setLicenseContent(await res.text());
        return;
      }

      setLicenseContent(`${res.status} Something Went Wrong!!!`);
    }

    getLicenseContent();
  }, []);

  return (
    <div className="p-4">
      <h2 className="my-4 text-2xl font-bold">LICENSE</h2>
      <div className="my-4 p-4 border-1 rounded">
        <p className="whitespace-break-spaces text-balance">{licenseContent}</p>
      </div>
      <button
        className="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        onClick={() => redirectToUrl(LicenseHtmlURL)}
      >
        View On Github
      </button>
    </div>
  );
}
