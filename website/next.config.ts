import type { NextConfig } from "next";

const isProd = process.env.NODE_ENV === "production";
const nextConfig: NextConfig = {
  output: "export",
  basePath: isProd ? "/Passcodes" : "",
  assetPrefix: isProd ? "/Passcodes/" : "",
};

export default nextConfig;
