import type { NextConfig } from "next";

const isProd = process.env.FLAVOR === "production";
const nextConfig: NextConfig = {
  output: "export",
  basePath: isProd ? "/Passcodes" : "",
  assetPrefix: isProd ? "/Passcodes/" : "",
};

export default nextConfig;
